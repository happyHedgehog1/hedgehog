package com.hedgehog.client.myshop.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ModifyForm {
    private String name;
    private Date birthday;
    private String gender;
    private String userId;
    private String userPwd;
    private String email;
    private String hiddenCertifiedKey;
    private String phone;
    private String emailService;
}
