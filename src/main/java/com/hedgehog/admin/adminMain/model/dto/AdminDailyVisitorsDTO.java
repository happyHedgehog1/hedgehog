package com.hedgehog.admin.adminMain.model.dto;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDailyVisitorsDTO {
    private int user_code;
    private String id;
    private LocalDateTime connection_date;
}
