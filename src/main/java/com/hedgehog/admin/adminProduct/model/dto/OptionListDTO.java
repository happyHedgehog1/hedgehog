package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OptionListDTO {
    private int productCode;
    private String optionCode;
    private int stock;
    private String exposureStatus;
    private int sales;
}
