package com.poly.thuviendatn.Service;

import com.poly.thuviendatn.Model.Account;
import com.poly.thuviendatn.Model.Role;
import com.poly.thuviendatn.Repository.AccountRepository;
import com.poly.thuviendatn.Repository.RoleRepository;
import com.poly.thuviendatn.Dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerAccount(RegisterFormDTO form) {
        // Check if username or email already exists
        if (accountRepository.findByUsername(form.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }
        if (accountRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        // Create new account
        Account account = new Account();
        account.setUsername(form.getUsername());
        account.setEmail(form.getEmail());
        account.setPassword(passwordEncoder.encode(form.getPassword()));
        account.setActive(true);

        // Assign USER role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));
        account.setRole(userRole);

        accountRepository.save(account);
    }

public Account findByUsername(String username) {
    return accountRepository.findByUsername(username)
        .map(account -> {
            account.getRole(); // Ensure role is fetched
            return account; // Return the mapped account
        })
        .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản với username: " + username));
}


}