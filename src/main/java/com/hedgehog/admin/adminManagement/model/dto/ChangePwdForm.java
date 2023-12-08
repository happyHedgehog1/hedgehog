package com.hedgehog.admin.adminManagement.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChangePwdForm {
    private int userCode;
    private String adminUpdatePass;
}
