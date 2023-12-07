package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductForm {
    private String prdCondition;
    private String serachCondition;
    private String searchValue;
    private String prdSerchStartPrice;
    private String prdSerchEndPrice;
    private String upperCategoryCode;
    private int subCategoryName;
    private String proSearchStartDay;
    private String proSearchEndDay;
}