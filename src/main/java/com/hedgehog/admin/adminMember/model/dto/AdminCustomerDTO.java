package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminCustomerDTO {

    private int customer_code;
    private String member_state;
    private String email;
    private String phone;
    private String certification_number;
    private String id;

}
