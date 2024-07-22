package ru.bitoche.basemarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.bitoche.basemarket.models.AppUser;
import ru.bitoche.basemarket.models.UserPass;
import ru.bitoche.basemarket.services.AppUserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private AppUserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam(name="email") String email,
            @RequestParam(name="password") String password,
            @RequestParam(name="firstName") String firstName,
            @RequestParam(name="secondName") String secondName,
            @RequestParam(name="thirdName") @Nullable String thirdName){
        AppUser user = new AppUser();
        user.setEnabled(false);
        user.setRoles("");
        user.setEmail(email);
        PasswordEncoder pe = new BCryptPasswordEncoder();
        user.setPassword(pe.encode(password));
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        if(thirdName!=null){
            user.setThirdName(thirdName);
        }
        return userService.saveUser(user, password);
    }


    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, Boolean> checkUsernameAvailability(@RequestParam("email") String email) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !userService.existsByUsername(email));
        return response;
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }


}
