package com.hedgehog.client.auth.model.service;

import com.hedgehog.client.auth.model.dto.LoginDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*로그인 할때 정보를 가져온다.*/
@Service
public class LoginService implements UserDetailsService {
    private final AuthService authService;

    public LoginService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public LoginDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authService.findByUserId(username);
    }
}
