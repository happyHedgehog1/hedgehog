package com.hedgehog.admin.adminOrder.model.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminOrderForm {

    private String state;
    private String keyword;
    private String keywordValue;
    private String searchStartDay;
    private String searchEndDay;




}
