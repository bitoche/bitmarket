package ru.bitoche.basemarket.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bitoche.basemarket.features.Encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class PasswordRecoveryToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="recovery_token")
    private String recoveryToken;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public PasswordRecoveryToken(AppUser appUser, String newPassword) throws Exception {
        recoveryToken = Encoder.encodeString(appUser.getId()+
                ";"+
                appUser.getEmail()+
                ";"+
                newPassword);
        createdDate = new Date();
    }

    public String getRecoveryToken() {
        return this.recoveryToken;
    }

    public static Long getUserId(PasswordRecoveryToken passwordRecoveryToken) throws Exception {
        var recoveryToken = Encoder.decodeString(passwordRecoveryToken.getRecoveryToken());
        for (String part:
                recoveryToken.split(";")) {
            return Long.parseLong(part); // первый парт - точно айди
        }
        return null;
    }
    public static String getNewUserPassword(PasswordRecoveryToken passwordRecoveryToken) throws Exception {
        var recoveryToken = Encoder.decodeString(passwordRecoveryToken.getRecoveryToken());
        List<String> tokenParts = new ArrayList<>(Arrays.asList(recoveryToken.split(";")));
        return tokenParts.get(tokenParts.size()-1);
    }
}
