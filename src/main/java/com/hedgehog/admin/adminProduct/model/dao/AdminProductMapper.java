package com.hedgehog.admin.adminProduct.model.dao;

import com.hedgehog.admin.adminProduct.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminProductMapper {


//    List<AdminProductDTO> searchProduct(AdminProductForm form);

    List<AdminProductDTO> selectAllProductList();

    List<AdminProductDTO> searchProduct(AdminProductForm form);

    int addProduct(AdminProductAddForm product);

    int addOption(AdminProductAddForm product);

    int addImg(AttachmentDTO attachmentList);

    List<AdminCategoryDTO> searchOption(int upperCategoryCode);

    int addOptionList(AdminProductAddForm product);
}
