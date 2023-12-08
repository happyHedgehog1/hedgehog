package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductAddForm {
    private char orderableStatus;
    private String salesStart;
    private String salesEnd;
    private String productName;
    private int price;
    private int subCategoryName;
    private int deliveryCharge;
    private String optionCode;
    private String optionName;
    private int sales;
    private int stock;
    private int productCode;
    private int category_code;
    private List<AttachmentDTO> attachmentList;

}
