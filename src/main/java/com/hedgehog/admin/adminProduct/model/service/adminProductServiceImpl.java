package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dao.adminProductMapper;
import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class adminProductServiceImpl implements adminProductService{

    private final adminProductMapper mapper;
    public adminProductServiceImpl(adminProductMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<adminProductDTO> searchProduct(adminProductForm form) {
        List<adminProductDTO> productList = mapper.searchProduct(form);

        return productList;
    }

}
