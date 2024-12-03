package com.wtix.wtix.controller;

import com.wtix.wtix.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

@Controller
public abstract
class SessionController {

    public boolean validateSession(HttpSession session){
        User user = (User)session.getAttribute("user_session");
        if(user == null){
            return false;
        }
        return true;
    }

    public String failedSessionHandler(){
        return "redirect:/";
    }


}
