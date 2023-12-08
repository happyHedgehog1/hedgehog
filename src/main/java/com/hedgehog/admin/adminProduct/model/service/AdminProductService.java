package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dto.*;
import com.hedgehog.admin.exception.AdminProductAddException;

import java.util.List;

public interface AdminProductService {

//    public List<AdminProductDTO> selectAllProductList();

    List<AdminProductDTO> searchProduct(AdminProductForm form);

    public void productAdd(AdminProductDTO product) throws AdminProductAddException;

    List<AdminCategoryDTO> findOptionList(int categoryCode);

    OptionDTO searchOption(OptionDTO optionDTO);

    public void productAddExcludeOptionCode(AdminProductDTO product) throws AdminProductAddException;
}
