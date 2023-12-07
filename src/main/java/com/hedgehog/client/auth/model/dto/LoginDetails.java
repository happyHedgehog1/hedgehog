package com.hedgehog.client.auth.model.dto;


import com.hedgehog.common.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 로그인 자체는 loginDetails로 한다. 권한에 따라 작업가능한 영역이 달라진다.
public class LoginDetails implements UserDetails {
    private LoginUserDTO loginUserDTO;

    public LoginDetails() {
    }

    public LoginDetails(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    public LoginUserDTO getLoginUserDTO() {
        return loginUserDTO;
    }

    public void setLoginUserDTO(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority userAuthority = null;
        UserRole loginUserRole = loginUserDTO.getRole();
        if(loginUserRole.getRole().equals(UserRole.MEMBER.getRole())) {
            userAuthority = new SimpleGrantedAuthority(UserRole.MEMBER.getRole());
        }else if(loginUserRole.getRole().equals(UserRole.ADMIN.getRole())){
            userAuthority = new SimpleGrantedAuthority(UserRole.ADMIN.getRole());
        }else if(loginUserRole.getRole().equals(UserRole.SUPER_ADMIN.getRole())){
            userAuthority = new SimpleGrantedAuthority(UserRole.SUPER_ADMIN.getRole());
        }
        authorities.add(userAuthority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return loginUserDTO.getUserPwd();
    }

    @Override
    public String getUsername() {
        return loginUserDTO.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
