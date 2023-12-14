package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminAutoMailDTO {
    private int formCode;
    private String name;
    private String content;
    private String creationDate;
    private String modifyDate;
    private String title;
}
