package ru.bitoche.basemarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.bitoche.basemarket.models.AppUserDetails;
import ru.bitoche.basemarket.services.AppUserDetailsService;

import java.util.ArrayList;
import java.util.Date;
@SpringBootApplication
public class BasemarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasemarketApplication.class, args);
        /*Если создаем пользователя вручную, то надо зашифровать пароль
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("user"));*/

    }

}
