package com.hedgehog.client.product.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailReviewDTO {

    private String ReviewContent;
    private String ReviewWriteDate;
    private String ReviewId;
    private double ReviewAvg;
    private int Review;
    private int ReviewCode;
}
