package com.poly.thuviendatn.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poly.thuviendatn.Model.Account;
import com.poly.thuviendatn.Service.AccountService;
@Controller
public class EditProfileController {

    @Autowired
    private AccountService userService;

    @GetMapping("/profile")
    public String showProfileForm(Model model, Principal principal) {
        String username = principal.getName(); // lấy username từ session
        Account account = userService.findByUsername(username); // lấy thông tin tài khoản từ DB

        model.addAttribute("user", account); // đẩy dữ liệu ra form
        return "Public/editprofile"; // đường dẫn tới file Thymeleaf
    }
}
