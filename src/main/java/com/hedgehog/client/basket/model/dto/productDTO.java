package com.hedgehog.client.basket.model.dto;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class productDTO {

    private int productCode;
    private int categoryCode; //categoryDTO
    private String productName;
    private String orderableStatus;
    private int price;
    private Date registrationDate;
    private Date modificationDate;
    private String eventProgressionstatus;
    private int deliveryChange;
    private Date salesStart;
    private int reviews;
    private int grade;
    private Date salesEnd;




}
