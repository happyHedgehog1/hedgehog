package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminFAQDTO {
    private int post_code;
    private int user_code;
    private String write_date;
    private String modify_date;
    private String content;
    private String title;
    private int views;
    private String post_type;
    private String state;
}
