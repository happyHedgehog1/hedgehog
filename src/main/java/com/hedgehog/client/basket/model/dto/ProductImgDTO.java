package com.hedgehog.client.basket.model.dto;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductImgDTO {
    private int imgCode;
    private String imageClassification;
    private String sourcePath;
    private String convertPath;
    private String sourceName;
    private String convertName;
    private String imageStatus;
    private Date createDate;
    private Date modifyDate;
    private int productCode;

}
