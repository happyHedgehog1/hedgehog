package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class adminProductDTO {
private int productCode;
private int categoryCode;
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
private optionListDTO option;



}
