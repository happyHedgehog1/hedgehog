package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class adminUnregisterDTO {
    private int withdraw_code;
    private int user_code;
    private String apply_date;
    private String commit_date;
    private String cause;
    private String state;
    private String cancel_date;
    private adminUserDTO user;
    private String id;




}
