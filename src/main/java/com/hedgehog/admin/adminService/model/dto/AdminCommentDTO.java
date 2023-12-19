package com.hedgehog.admin.adminService.model.dto;

import com.hedgehog.admin.adminMember.model.dto.AdminUserDTO;
import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminCommentDTO {
    private int comment_code;
    private int inquiry_code;
    private int user_code;
    private Date write_date;
    private Date modify_date;
    private String content;
    private String answer_state;
    private String email;
    private AdminInquiryDTO adminInquiryDTO;
    private AdminUserDTO adminUserDTO;

}
