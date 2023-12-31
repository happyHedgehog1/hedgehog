package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dto.*;
import com.hedgehog.admin.exception.AdminProductAddException;

import java.util.List;

public interface AdminProductService {

//    public List<AdminProductDTO> selectAllProductList();

    List<AdminProductDTO> searchProduct(AdminProductForm form);

    public void productAdd(AdminProductDTO product) throws AdminProductAddException;

    List<AdminCategoryDTO> findCategoryList(int categoryCode);


    public AdminProductDTO selectProductDetail(int productCode);

    public void productUpdate(AdminProductDTO product) throws AdminProductAddException;

    List<AdminCategoryDTO> categoryList(AdminCategoryDTO category);

    List<AdminProductDTO> categoryDetail(String categoryCode);

    public void categoryModify(AdminCategoryForm categoryForm) throws AdminProductAddException;
}
