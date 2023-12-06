package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dao.adminProductMapper;
import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import com.hedgehog.admin.exception.AdminProductAddException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class adminProductServiceImpl implements adminProductService{

    private final adminProductMapper mapper;
    public adminProductServiceImpl(adminProductMapper mapper) {
        this.mapper = mapper;
    }


//    @Override
//    public List<adminProductDTO> selectAllProductList() {
//        List<adminProductDTO> productList = mapper.selectAllProductList();
//        return productList;
//    }



    @Override
    public List<adminProductDTO> searchProduct(adminProductForm form) {
        List<adminProductDTO> productList = mapper.searchProduct(form);

        return productList;
    }

    @Override
    @Transactional
    public void productAdd(adminProductDTO product) throws AdminProductAddException {
        log.info("================================="+product.toString());

        int addProduct = mapper.addProduct(product);
        int addOption = mapper.addOption(product);
        int addImg = mapper.addImg(product);


        if(!(addProduct > 0) || !(addOption > 0) || !(addImg > 0)) {
            throw new AdminProductAddException("상품 등록에 실패하셨습니다.");
        }
    }


}
