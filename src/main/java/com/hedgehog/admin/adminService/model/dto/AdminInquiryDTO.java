package com.hedgehog.admin.adminService.model.dto;

import com.hedgehog.admin.adminMember.model.dto.AdminMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUserDTO;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminInquiryDTO {
    private int inquiry_code;
    private int member_code;
    private String secret_state;
    private String password;
    private String answer_state;
    private String write_date;
    private String modify_date;
    private String post_type;
    private String title;
    private String content;
    private String state;
    private AdminUserDTO id;


}
