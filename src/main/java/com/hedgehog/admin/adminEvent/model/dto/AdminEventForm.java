package com.hedgehog.admin.adminEvent.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminEventForm {
    private String searchStartDay;
    private String searchEndDay;
    private String eventName;
    private String status;
    private String prdKeyword;
    private String searchValue;
    private int subCategoryName;
    private int searchStartPrice;
    private int searchEndPrice;
    private double price;
    private List<String> allProductCodes;
    private int post_code;
    private List<String > productCode;
}
