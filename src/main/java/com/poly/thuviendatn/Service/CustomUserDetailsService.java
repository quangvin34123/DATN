package com.poly.thuviendatn.Service;

import com.poly.thuviendatn.Model.Account;
import com.poly.thuviendatn.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (!account.isActive()) {
            throw new UsernameNotFoundException("Account is disabled");
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + account.getRole().getName());
        return new User(
                account.getUsername(),
                account.getPassword(),
                Collections.singletonList(authority)
        );
    }
}