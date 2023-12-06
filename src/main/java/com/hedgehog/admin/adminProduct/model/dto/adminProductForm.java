package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class adminProductForm {
    private String prdCondition;
    private String serachCondition;
    private String serachValue;
    private String prdSerchStartPrice;
    private String prdSerchEndPrice;
    private String category1;
    private String category2;
    private String proSearchStartDay;
    private String proSearchEndDay;
}