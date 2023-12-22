package com.hedgehog.client.clientOrder.model.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString



public class ClientCartOrderForm {




    private String orderCode;
    private String finalPrice;
    private String productName;
    private String userCode;

}
