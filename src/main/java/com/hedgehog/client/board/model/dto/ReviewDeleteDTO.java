package com.hedgehog.client.board.model.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDeleteDTO {
    private int grade;
    private int productCode;
}
