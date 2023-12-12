package com.hedgehog.admin.adminService.model.dto;

import com.hedgehog.admin.adminMember.model.dto.AdminUserDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.AttachmentDTO;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminReviewDTO {
    private int review_code;
    private int product_code;
    private String option_code;
    private int grade;
    private String title;
    private String content;
    private String write_date;
    private int member_code;
    private AttachmentDTO attachment;
    private AdminProductDTO product;
    private AdminUserDTO id;
}
