package com.poly.thuviendatn.Controller;

import com.poly.thuviendatn.Dto.RegisterFormDTO;
import com.poly.thuviendatn.Service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("accountForm", new RegisterFormDTO());
        return "Public/register";
    }

    @PostMapping("/register")
    public String processRegisterForm(@Valid @ModelAttribute("accountForm") RegisterFormDTO form,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Public/register";
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("passwordMismatch", true);
            return "Public/register";
        }
        try {
            accountService.registerAccount(form);
            model.addAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "Public/register";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "Public/register";
        }
    }
}