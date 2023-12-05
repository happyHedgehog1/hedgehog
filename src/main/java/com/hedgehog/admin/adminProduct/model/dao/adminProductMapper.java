package com.hedgehog.admin.adminProduct.model.dao;

import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface adminProductMapper {


//    List<adminProductDTO> searchProduct(adminProductForm form);


    List<adminProductDTO> selectAllProductList();
}
