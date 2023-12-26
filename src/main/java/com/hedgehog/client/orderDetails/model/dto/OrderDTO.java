package com.hedgehog.client.orderDetails.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String state;
    private LocalDate dateStart;
    private LocalDate dateEnd;
}
