package ru.bitoche.basemarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public ConfirmationToken(AppUser user) {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        confirmationToken = user.getId() + ";" + pe.encode(user.getFirstName()
                +user.getSecondName()
                +user.getThirdName()
                +user.getPassword()
                +user.getEmail()
        );
        createdDate = new Date();
    }

    public String getConfirmationToken() {
        return this.confirmationToken;
    }

    public Long getUserEntityId() {
        for (String part:
                confirmationToken.split(";")) {
            return Long.parseLong(part); // первый парт - точно айди
        }
        return null;
    }
}
