package com.poly.thuviendatn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

@GetMapping("/login")
public String showLoginPage() {
    return "Account/login"; // Thymeleaf sẽ tìm tệp login.html trong thư mục templates/Account/
}
}
