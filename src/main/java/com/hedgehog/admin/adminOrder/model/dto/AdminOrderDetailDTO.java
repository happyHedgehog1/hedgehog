package com.hedgehog.admin.adminOrder.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminOrderDetailDTO {
    private int orderDetailsCode;
    private int count;
    private int productCode;
    private int orderCode;
    private int deliveryCharge;
    private int point;
    private int reducedPrice;
    private int costPrice;
    private int finalPrice;
    private String optionCode;
}
