package com.hedgehog.client.clientOrder.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderInfoDTO {

    private String name;
    private String phone;
    private String email;
    private String orderDetailsCode;
    private String finalPrice;


}
