package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminCategoryDTO {
    private int subCategoryName;
    private String state;
    private String name;
    private int upperCategoryCode;
}
