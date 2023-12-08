package com.hedgehog.client.auth.model.dto;


import com.hedgehog.common.enums.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationForm{
    private boolean agree1;
    private boolean agree2;
    private String name;
    private Date birthday;
    private String gender;
    private String userId;
    private String userPwd;
    private String email;
    private String emailAuthenticationNumber;
    private String hiddenCertifiedKey;
    private String emailService;
    private String phone;
}
