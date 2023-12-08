package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class adminUserDTO {

    private int user_code;
    private String id;
    private String password;
    private String classify;
    private String name;
    private String connection_date;
    private String creation_date;
    private String withdraw_state;

}
