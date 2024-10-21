package ru.bitoche.basemarket.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bitoche.basemarket.features.Logger;
import ru.bitoche.basemarket.models.AppUser;
import ru.bitoche.basemarket.models.ConfirmationToken;
import ru.bitoche.basemarket.models.PasswordRecoveryToken;
import ru.bitoche.basemarket.repositories.ConfirmationTokenRepository;
import ru.bitoche.basemarket.repositories.IAppUserRepository;
import ru.bitoche.basemarket.repositories.IPasswordRecoveryTokenRepository;

import java.util.Date;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AppUserService {

    @Autowired
    IAppUserRepository appUserRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    IPasswordRecoveryTokenRepository passwordRecoveryTokenRepository;
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
        mailMessage.setFrom("tszyuconstantin@yandex.ru");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Завершение регистрации BitMarket");
        mailMessage.setText("Чтобы завершить регистрацию, пожалуйста пройдите по ссылке ниже:\n"
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken()
                +"\n\nВаш логин для входа: "+user.getEmail()+"\nВаш пароль для входа: "+decodedPass+"\n\nДанное письмо сформировано автоматически, на него не нужно отвечать.");
        emailService.sendEmail(mailMessage);


        Logger.log(this.getClass(), "Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Письмо с ссылкой-подтверждением успешно отправлено на ваш почтовый ящик.");
    }
    public void cleanRecieveTokens(){
        passwordRecoveryTokenRepository.deleteAll(passwordRecoveryTokenRepository.findAll().stream().filter(t -> t.getCreatedDate().before(new Date())).findAny().stream().toList());
        Logger.log(this.getClass(),"recieve tokens cleaned!");
    }
    public ResponseEntity<?> doRecoveryPass(AppUser user, String newPass) throws Exception {

        if (!appUserRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Ошибка: Такого email не зарегистрировано! Проверьте правильность ввода данных.");
        }

        PasswordRecoveryToken passwordRecoveryToken = new PasswordRecoveryToken(user, newPass);
        if(passwordRecoveryTokenRepository.findPasswordRecoveryTokenByRecoveryToken(passwordRecoveryToken.getRecoveryToken())!=null){
            return ResponseEntity.badRequest().body("Ошибка: На данный e-mail уже выслана ссылка для восстановления доступа. Проверьте почтовый ящик.");
        }

        cleanRecieveTokens();

        passwordRecoveryTokenRepository.save(passwordRecoveryToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("tszyuconstantin@yandex.ru");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Восстановление доступа BitMarket");
        mailMessage.setText("Чтобы завершить восстановление доступа, пожалуйста пройдите по ссылке ниже:\n"
                +"http://localhost:8080/do-pass-recovery?token="+passwordRecoveryToken.getRecoveryToken()
                +"\n<small>Ссылка действительна в течение сегодняшнего дня. (по MSK)</small>"
                +"\n\nВаш логин для входа: "+user.getEmail()+"\nВаш пароль для входа: "+newPass+"\n\nДанное письмо сформировано автоматически, на него не нужно отвечать.");
        emailService.sendEmail(mailMessage);


        Logger.log(this.getClass(), "Recovery Token: " + passwordRecoveryToken.getRecoveryToken());

        return ResponseEntity.ok("Письмо с восстановительной ссылкой успешно отправлено на ваш почтовый ящик.");
    }
    public ResponseEntity<?> resetPass(String recoveryToken) throws Exception {
        PasswordRecoveryToken token = passwordRecoveryTokenRepository.findPasswordRecoveryTokenByRecoveryToken(recoveryToken);
        if(token!=null){
            AppUser user = appUserRepository.findByEmailIgnoreCase(appUserRepository.findById(PasswordRecoveryToken.getUserId(token)).get().getEmail());
            PasswordEncoder pe = new BCryptPasswordEncoder();
            user.setPassword(
                    pe.encode(
                            PasswordRecoveryToken.getNewUserPassword(token))
                    );
            appUserRepository.save(user);
            passwordRecoveryTokenRepository.delete(passwordRecoveryTokenRepository.findPasswordRecoveryTokenByRecoveryToken(recoveryToken));
            return ResponseEntity.ok("Новый пароль успешно установлен!");
        }
        Logger.log(this.getClass(),"error: recovery token isn't correct: "+recoveryToken);
        return ResponseEntity.badRequest().body("Ошибка: Пароль не изменен (Bad request)");
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
