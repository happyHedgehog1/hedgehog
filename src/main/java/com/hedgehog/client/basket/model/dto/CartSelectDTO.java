package com.hedgehog.client.basket.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CartSelectDTO {

    private int cartCode;
    private int productCode;
    private int imgCode;
    private String productName;
    private int price;
    private int savedMoney;
    private int amount;
    private int deliveryCharge;
    private int productSum;
    private String formattedPrice;
}
