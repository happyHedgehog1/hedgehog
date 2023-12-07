package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminCategoryDTO {
    private int subCategoryName;
    private char state;
    private String name;
    private int upperCategoryCode;
}
