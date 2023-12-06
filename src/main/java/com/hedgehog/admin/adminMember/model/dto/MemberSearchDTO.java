package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberSearchDTO {

    private int member_code;
    private String memberId;
    private String name;
    private String phone;
    private String email;
    private int cumulative_amount;
    private int point;
    private boolean email_consent;
    private Date creation_date;
    private Date connection_date;
    private boolean withdraw_state;

}
