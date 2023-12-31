package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminMemberDTO {
    private int member_code;
    private Date birthday;
    private String gender;
    private String email_consent;
    private int point;

    private int cumulative_amount;

}
