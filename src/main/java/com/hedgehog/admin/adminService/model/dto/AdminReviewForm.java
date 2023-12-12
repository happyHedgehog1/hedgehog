package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminReviewForm {
    private String revSearchStartDay;
    private String revSearchEndDay;
    private String revCondition;
    private String revKeyword;
    private String revSearch;
}
