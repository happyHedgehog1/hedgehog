package com.hedgehog.client.list.dto;

import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.AttachmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@ToString
@NoArgsConstructor
@Service
@AllArgsConstructor
public class ProductListDTO {

    private AdminProductDTO adminProductDTO;
    private AttachmentDTO attachmentDTO;
}



