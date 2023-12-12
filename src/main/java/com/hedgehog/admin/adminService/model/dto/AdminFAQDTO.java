package com.hedgehog.admin.adminService.model.dto;

import com.hedgehog.admin.adminMember.model.dto.AdminUserDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminFAQDTO {
    private int user_code;
    private String title;
    private String content;
    private String write_date;
    private String modify_date;
    private int views;
    private String state;
    private AdminUserDTO id;


}
