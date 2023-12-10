package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminUnregisterForm {

    private String idSearch;
    private String searchId;
    private String unregisterCondition;
    private String applyStartDay;
    private String applyEndDay;
    private String commitStartDay;
    private String commitEndDay;
}
