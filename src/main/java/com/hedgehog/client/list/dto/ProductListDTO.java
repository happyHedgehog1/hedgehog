package com.hedgehog.client.list.dto;

import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.AttachmentDTO;
import lombok.*;
import org.springframework.security.web.PortResolverImpl;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductListDTO {

    private double Discount;
    private String SaleEndDay;
    private String saleOrBestCode;
    private AdminProductDTO adminProductDTO;
    private AttachmentDTO attachmentList;
    private AdminCategoryDTO adminCategoryDTO;

}



