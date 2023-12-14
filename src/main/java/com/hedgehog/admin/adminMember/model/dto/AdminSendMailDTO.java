package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminSendMailDTO {
    private int formCode;
    private String name;
    private String content;
    private String creationDate;
    private String modifyDate;
    private String title;
    private List<String> memberList;

}
