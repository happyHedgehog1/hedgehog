package com.hedgehog.client.list.dto;

import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.AttachmentDTO;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductListDTO {

    private AdminProductDTO adminProductDTO;
    private List<AttachmentDTO> attachmentList;
    private AdminCategoryDTO adminCategoryDTO;

}


