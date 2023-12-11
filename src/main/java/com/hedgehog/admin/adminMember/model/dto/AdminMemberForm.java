package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminMemberForm {

    private String dateStart;
    private String dateEnd;
    private String memKeyword;
    private String memAgree;
    private String memCount;
    private String searchKeyword;


}
