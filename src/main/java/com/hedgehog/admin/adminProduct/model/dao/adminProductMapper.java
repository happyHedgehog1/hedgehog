package com.hedgehog.admin.adminProduct.model.dao;

import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface adminProductMapper {


//    List<adminProductDTO> searchProduct(adminProductForm form);


    List<adminProductDTO> selectAllProductList();

    List<adminProductDTO> searchProduct(adminProductForm form);
}
