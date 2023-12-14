package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class AdminCategoryForm {
    private String categoryName;
    private String upperCategoryCode;
    private String subCategoryName;
    private String categoryDisplay;
    private String inputCategoryName;

}
