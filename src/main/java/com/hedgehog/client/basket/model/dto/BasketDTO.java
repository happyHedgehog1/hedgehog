package com.hedgehog.client.basket.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString


public class BasketDTO {

    private int cartCode;
    private int amount;
    private int customerCode;
    private int optionCode;
    private ProductDTO productCode;
    private ProductImgDTO imgCode;



}
