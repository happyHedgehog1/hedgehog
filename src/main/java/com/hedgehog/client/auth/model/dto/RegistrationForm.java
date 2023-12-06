package com.hedgehog.client.auth.model.dto;


import lombok.*;

import java.sql.Date;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter@ToString
public class RegistrationForm {
    private boolean agree1;
    private boolean agree2;
    private String name;
    private Date birthday;
    private String gender;
    private String userPwd;
    private String emailAuthenticationNumber;
    private String hiddenCertifiedKey;
    private String phone;
}
