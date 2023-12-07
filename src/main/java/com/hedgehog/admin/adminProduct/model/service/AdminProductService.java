package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dto.AdminCategoryDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductForm;
import com.hedgehog.admin.adminProduct.model.dto.OptionDTO;
import com.hedgehog.admin.exception.AdminProductAddException;

import java.util.List;

public interface AdminProductService {

//    public List<AdminProductDTO> selectAllProductList();

    List<AdminProductDTO> searchProduct(AdminProductForm form);

    public void productAdd(AdminProductDTO product) throws AdminProductAddException;

    List<AdminCategoryDTO> findOptionList(int categoryCode);
}
