package com.hedgehog.client.list.dto;

import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryDTO;
import com.hedgehog.admin.adminProduct.model.dto.AttachmentDTO;
import com.hedgehog.admin.adminProduct.model.dto.OptionDTO;
import com.hedgehog.admin.adminProduct.model.dto.OptionListDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminProductDTO2 {
    private int productCode;
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
    private OptionListDTO option;
    private List<AttachmentDTO> attachment;
    private OptionDTO optionDTO;
    private AdminCategoryDTO category;


}
