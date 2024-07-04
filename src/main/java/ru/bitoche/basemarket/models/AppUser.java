package ru.bitoche.basemarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String password;
    @Column(unique = true)
    private String email;
    private boolean isEnabled;
    private String roles;

    private String firstName;
    private String secondName;
    @Nullable
    private String thirdName;

    public String getFullName(){
        String fullName = secondName+" "+firstName;
        if(thirdName!=null){
            fullName+=" "+thirdName;
        }
        return fullName;
    }
    private List<String> getAllRoles(){
        if(!(getRoles()==null) && !getRoles().isEmpty()){
            return new ArrayList<>(Arrays.asList(this.roles.split(" ")));
        }
        return null;
    }
    public boolean haveAccessToChangeTags(){
        var allRoles = getAllRoles();
        return allRoles!=null && allRoles.contains("MODER");
    }
}
