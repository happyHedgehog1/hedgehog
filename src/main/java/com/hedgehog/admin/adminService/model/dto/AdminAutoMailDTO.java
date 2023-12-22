package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

import java.util.List;

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
    private int mail_code;
    private List<String> searchEmailList;
    private String mailList;
    private int eventCode;
    private String searchStartDay;
    private String searchEndDay;
    private String search;
    private String keyword;
    private String date;
    private String mailsCode;
}
