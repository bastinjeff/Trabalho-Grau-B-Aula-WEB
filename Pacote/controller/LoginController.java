package Pacote.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController{

    @RequestMapping("loginForm")
    public String loginForm() {
        return "login/formulario-login";
    }
    
}