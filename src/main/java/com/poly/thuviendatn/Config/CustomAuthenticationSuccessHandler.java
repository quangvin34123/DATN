package com.poly.thuviendatn.Config;

import com.poly.thuviendatn.Model.Account;
import com.poly.thuviendatn.Repository.AccountRepository;
import com.poly.thuviendatn.Service.EmailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        System.out.println("AccountRepository: " + accountRepository);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String username = authentication.getName();
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        if (account.getEmail() != null && !account.getEmail().isEmpty()) {
            emailService.sendLoginNotification(account.getEmail(), username);
        }

        String redirectUrl = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")) ? "/" : "/";
        response.sendRedirect(redirectUrl);
    }
}