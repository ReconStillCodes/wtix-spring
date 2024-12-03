package com.wtix.wtix.controller;

import com.wtix.wtix.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewLogin(){
        return "login";
    }


    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password, Model model, HttpSession session){

        if(userService.validateLogin(email, password)){
            session.setAttribute("user_session", userService.getUserByEmail(email));
            session.setMaxInactiveInterval(60 * 60 * 60 *3);
            System.out.println(session.getAttribute("user_session"));
            model.addAttribute("error", "");
            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid Credentials. Please try again.");
        return "login";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
