package com.hedgehog.admin.adminService.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminAutoMailForm {
    private String searchStartDay;
    private String searchEndDay;
    private String search;
    private String keyword;
}
