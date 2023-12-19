package com.hedgehog.admin.adminMain.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminMainStatisticsDTO {
    private List<String> sales;
    private List<String> visitor;
    private List<String> reviews;
    private List<String> saleVolume;
    private List<String> newMember;
    private List<String> inquiry;





}
