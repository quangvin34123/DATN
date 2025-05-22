package com.poly.thuviendatn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "Public/login"; // login.html trong templates/Public/
    }

    @GetMapping("/password/success")
    public String passwordChangeSuccess() {
        return "Public/password-change-success"; // success-password.html trong templates/Account/
    }

    @GetMapping("/password/fail")
    public String passwordChangeFail() {
        return "Public/password-change-failed"; // fail-password.html trong templates/Account/
    }
}
