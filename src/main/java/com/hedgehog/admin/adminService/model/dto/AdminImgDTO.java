package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminImgDTO {
    private int post_img_code;
    private int post_code;
    private Date create_date;
    private Date modify_date;
    private String source_path;
    private String convert_path;
    private String source_name;
    private String convert_name;
    private String state;
    private String post_type;

}
