package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

import java.util.List;

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
    private List<OptionDTO> optionDTO;
}
