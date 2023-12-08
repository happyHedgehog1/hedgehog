package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class adminMemberForm {

    private String dateStart;
    private String dateEnd;
    private String memKeyword;
    private String memAgree;
    private String memCount;
    private String searchKeyword;


}
