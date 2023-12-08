package com.hedgehog.admin.adminManagement.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminRegistrationForm {
    private String adminAddId;
    private String adminAddPass;
    private String adminAddName;
}
