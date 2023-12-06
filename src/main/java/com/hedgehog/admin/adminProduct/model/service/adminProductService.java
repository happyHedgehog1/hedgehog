package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;

import java.util.List;
import java.util.Map;

public interface adminProductService {

//    public List<adminProductDTO> selectAllProductList();

    List<adminProductDTO> searchProduct(adminProductForm form);
}
