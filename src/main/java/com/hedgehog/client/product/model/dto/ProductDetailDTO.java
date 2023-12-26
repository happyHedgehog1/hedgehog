package com.hedgehog.client.product.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailDTO {

    private int productCode;
    private String convertPath;
    private String imageClassification;
    private List<ProductTextDTO> productText;


}
