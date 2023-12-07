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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private String userId;
    private String userPwd;
    private String name;
    private String email;
    private String phone;
    private Date birthday;
    private String gender;
    private String hiddenCertifiedKey;
    private String emailService;

}
