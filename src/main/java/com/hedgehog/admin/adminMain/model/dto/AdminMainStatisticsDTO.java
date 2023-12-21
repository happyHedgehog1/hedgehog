package com.hedgehog.admin.adminMain.model.dto;

import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminReviewDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminMainStatisticsDTO {
    private int sales;
    private int visitor;
    private int reviews;
    private int saleVolume;
    private int newMember;
    private int inquiry;
    private int user;
    private int order;
    private int delivery;
    private List<AdminInquiryDTO> adminInquiryDTO;
    private AdminReviewDTO reviewDTO;






}
