package com.poly.thuviendatn.Service;

import com.poly.thuviendatn.Model.TaiKhoan;
import com.poly.thuviendatn.Repository.TaiKhoanRepository;
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
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String role = switch (taiKhoan.getQuyen().getMaQuyen()) {
            case 1 -> "ROLE_ADMIN";
            case 2 -> "ROLE_STAFF";
            case 3 -> "ROLE_USER";
            default -> throw new IllegalStateException("Invalid role: " + taiKhoan.getQuyen().getMaQuyen());
        };

        return new User(
                taiKhoan.getUsername(),
                taiKhoan.getPassword(),
                taiKhoan.isEnabled(), // Account enabled status
                true, // accountNonExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}