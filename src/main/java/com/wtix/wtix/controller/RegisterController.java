package com.wtix.wtix.controller;

import com.wtix.wtix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String viewRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model){

        if(!validateName(name) || !validateEmail(email) || !validatePassword(password)){
            model.addAttribute("error", "Invalid Credentials. Please try again.");
            return "register";
        }

        if(!userService.validateUniqueEmail(email)){
            model.addAttribute("error", "Email already Exists. Please try again.");
            return "register";
        }

        userService.createUser(name, email, password);

        model.addAttribute("error", "");
        return "redirect:/";
    }

    private boolean validateName(String name){
        if(name.isEmpty() || name.length() <= 0 || name.length() > 30){
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email){
        if(email.isEmpty() || email.length()<=0 || !email.contains("@")){
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password){
        if(password.isEmpty() || password.length() < 8 || password.length() > 20){
            return false;
        }
        return true;
    }



}
