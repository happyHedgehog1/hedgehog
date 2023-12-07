package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductDTO {
    private int productCode;
    private int subCategoryName;
    private String productName;
    private char orderableStatus;
    private int price;
    private Date registrationDate;
    private Date modificationDate;
    private char eventProgressionStatus;
    private int deliveryCharge;
    private String salesStart;
    private String salesEnd;
    private int reviews;
    private int grade;
    private OptionListDTO option;


}
