package com.hedgehog.client.basket.model.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartSumDTO {


    private int userCode;
    private int cartCode;
    private int amount;
    private int price;
    private int productCode;
    private int deliveryCharge;



}
