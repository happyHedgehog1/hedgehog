package com.hedgehog.client.myshop.model.dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfo {
    private String name;
    private Date birthday;
    private String gender;
    private String userId;
    private String email;
    private String emailService;
    private String phone;
}
