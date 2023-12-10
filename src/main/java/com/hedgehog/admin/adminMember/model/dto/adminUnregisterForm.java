package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class adminUnregisterForm {

    private String idSearch;
    private String searchId;
    private String unregisterCondition;
    private String startDay;
    private String endDay;
}
