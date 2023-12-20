package com.hedgehog.client.board.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductReviewDTO {
    private int reviews;
    private double grade;
}
