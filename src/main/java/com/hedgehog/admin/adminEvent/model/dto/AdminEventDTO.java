package com.hedgehog.admin.adminEvent.model.dto;

import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminEventDTO {
    private int postCode;
    private int writerCode;
    private String startDay;
    private String endDay;
    private String progress;
    private int views;
    private String status;
    private String title;
    private String content;
    private String writeDate;
    private String modifyDate;
    private double discount;
    private List<AdminEventProductListDTO> eventProductList;
    private List<AdminProductDTO> productDTOList;
}
