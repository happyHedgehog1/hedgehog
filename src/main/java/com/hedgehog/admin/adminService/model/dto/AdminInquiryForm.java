package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminInquiryForm {
    private String inqCondition;
    private String inqSearchStartDay;
    private String inqSearchEndDay;
    private String Condition;
    private String inqKeyword;
    private String inqSearch;


}
