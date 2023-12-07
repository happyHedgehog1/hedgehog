package com.hedgehog.admin.adminProduct.model.dao;

import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductForm;
import com.hedgehog.admin.adminProduct.model.dto.OptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminProductMapper {


//    List<AdminProductDTO> searchProduct(AdminProductForm form);

    List<AdminProductDTO> selectAllProductList();

    List<AdminProductDTO> searchProduct(AdminProductForm form);

    int addProduct(AdminProductDTO product);

    int addOption(AdminProductDTO product);

    int addImg(AdminProductDTO product);

    List<AdminCategoryDTO> searchOption(int upperCategoryCode);
}
