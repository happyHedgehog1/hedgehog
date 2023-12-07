package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dto.*;
import com.hedgehog.admin.exception.AdminProductAddException;

import java.util.List;

public interface AdminProductService {

//    public List<AdminProductDTO> selectAllProductList();

    List<AdminProductDTO> searchProduct(AdminProductForm form);

    public void productAdd(AdminProductAddForm product) throws AdminProductAddException;

    List<AdminCategoryDTO> findOptionList(int categoryCode);
}
