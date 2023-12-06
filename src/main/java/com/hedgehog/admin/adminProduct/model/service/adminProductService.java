package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import com.hedgehog.admin.exception.AdminProductAddException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
public interface adminProductService {

//    public List<adminProductDTO> selectAllProductList();

    List<adminProductDTO> searchProduct(adminProductForm form);

    public void productAdd(adminProductDTO product) throws AdminProductAddException;
}
