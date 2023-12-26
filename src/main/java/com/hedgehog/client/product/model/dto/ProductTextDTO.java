package com.hedgehog.client.product.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductTextDTO {

    private int productCode;
    private String optionName;
    private String productPrice;
    private String productName;
    private String saleOrBestCode;
    private String optionCode;

}
