package ru.bitoche.basemarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bitoche.basemarket.features.Logger;
import ru.bitoche.basemarket.services.AppUserService;
import ru.bitoche.basemarket.services.MainService;

import java.security.Principal;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    MainService mainService;
    @Autowired
    AppUserService appUserService;
    @GetMapping("/register")
    public String returnRegisterPage(){
        return "registration";
    }
    @GetMapping("/login")
    public String returnLoginPage(@Nullable boolean error, Model model){
        if(Objects.equals(error, true)){
            model.addAttribute("error", true);
        }
        return "authorization";
    }
    @GetMapping("/pass-recovery")
    public String returnPassRecoveryPage(){
        return "passrecovery";
    }
    //mainpage
    @GetMapping("/")
    public String returnMainPage(Model model, Principal principal){
        var pathToImg = "/res/objectImages/";
        model.addAttribute("imgFilePath", pathToImg);
        model.addAttribute("allObjects", mainService.getAllObjects());
        model.addAttribute("allTags", mainService.getAllTags());
        if (principal!=null){
            model.addAttribute("princ", appUserService.getUserByUsername(principal.getName()));
        }
        return "mainpage";
    }
    @GetMapping("/profile")
    public String getMyUser(Model model, Principal principal){
        if(principal!=null){
            model.addAttribute("princ", appUserService.getUserByUsername(principal.getName()));
            model.addAttribute("entryLink", "profile");
            return "userpage";
        }
        else return "login";
    }
    @PostMapping("/giveMeAccessToChangePass")
    public String giveMeAccessToChangePass(@RequestParam String userEnteredPass, Principal principal, Model model){
        if(principal!=null){
            model.addAttribute("princ", appUserService.getUserByUsername(principal.getName()));
            model.addAttribute("entryLink", "profile");

            PasswordEncoder pe = new BCryptPasswordEncoder();
            var userPass = appUserService.getUserByUsername(principal.getName()).getPassword();
            if(pe.matches(userEnteredPass, userPass)){ //matches сравнивает сырой пароль и хешированный
                model.addAttribute("isPassChangingAllowed", true);
                model.addAttribute("isInvalidPass", false);
                Logger.log(this.getClass(), principal.getName()+" pass valid: "+ userEnteredPass);
                Logger.log(this.getClass(),"--- continue | currentUserPassInDB: "+userPass+" (its hashed)");
            }
            else{
                model.addAttribute("isPassChangingAllowed", false);
                model.addAttribute("isInvalidPass", true);
                Logger.log(this.getClass(),principal.getName()+" pass invalid: "+ userEnteredPass );
                Logger.log(this.getClass(),"--- continue | currentUserPassInDB: "+userPass+" (its hashed)");
            }

            return "userpage";
        }
        else return "login";
    }
}
