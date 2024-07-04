package ru.bitoche.basemarket.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bitoche.basemarket.models.AppUser;
import ru.bitoche.basemarket.models.ConfirmationToken;
import ru.bitoche.basemarket.models.UserPass;
import ru.bitoche.basemarket.repositories.ConfirmationTokenRepository;
import ru.bitoche.basemarket.repositories.IAppUserRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AppUserService {
    @Autowired
    IAppUserRepository appUserRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    EmailService emailService;
    public boolean existsByUsername(String email){
        return appUserRepository.findAppUserByEmail(email).isPresent();
    }
    public AppUser getUserByUsername(String email){
        return existsByUsername(email) ? appUserRepository.findAppUserByEmail(email).get() : null;
    }

    public ResponseEntity<?> saveUser(AppUser user, String decodedPass) {

        if (appUserRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Ошибка: Такой email уже зарегистрирован!");
        }

        appUserRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Завершение регистрации BitMarket");
        mailMessage.setText("Чтобы завершить регистрацию, пожалуйста пройдите по ссылке ниже:\n"
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken()
                +"\n\nВаш логин для входа: "+user.getEmail()+"\nВаш пароль для входа: "+decodedPass+"\n\nДанное письмо сформировано автоматически, на него не нужно отвечать.");
        emailService.sendEmail(mailMessage);


        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Письмо с ссылкой-подтверждением успешно отправлено на ваш почтовый ящик.");
    }
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            AppUser user = appUserRepository.findByEmailIgnoreCase(appUserRepository.findById(token.getUserEntityId()).get().getEmail());
            user.setEnabled(true);
            appUserRepository.save(user);
            return ResponseEntity.ok("Ваш email успешно подтвержден!");
        }
        return ResponseEntity.badRequest().body("Ошибка: Email не подтвержден (Bad request)");
    }

}
