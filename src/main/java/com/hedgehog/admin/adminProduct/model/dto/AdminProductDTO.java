package com.hedgehog.admin.adminProduct.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductDTO {
    private int productCode;

    @NotNull(message = "1번")
    private int subCategoryName;
    private String productName;
    private String orderableStatus;
    private int price;
    private Date registrationDate;
    private Date modificationDate;
    private String  eventProgressionStatus;
    private int deliveryCharge;
    private String salesStart;
    private String salesEnd;
    private int reviews;
    private int grade;
    private String upperCategoryName;
    private List<OptionListDTO> optionList;
    private List<AttachmentDTO> attachment;
    private List<OptionDTO> optionDTO;
    private AdminCategoryDTO category;

    public int getProductCode() {
        return productCode;
    }
}
