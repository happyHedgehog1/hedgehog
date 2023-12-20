package com.hedgehog.admin.adminMain.model.dto;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDailySalesDTO {
    private int order_code;
    private int final_price;
    private LocalDateTime creation_date;
}
