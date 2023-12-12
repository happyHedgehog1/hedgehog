package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminFAQForm {
    private String faqCondition;
    private String faqSearchStartDay;
    private String faqSearchEndDay;
    private String Condition;
    private String faqKeyword;
    private String faqSearch;


//    =======공지사항=========
    private String notSearchStartDay;
    private String notSearchEndDay;
    private String notCondition;
    private String notKeyword;
    private String notSearch;

}
