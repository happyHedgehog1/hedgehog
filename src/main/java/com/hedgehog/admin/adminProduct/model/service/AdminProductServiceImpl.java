package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dao.AdminProductMapper;
import com.hedgehog.admin.adminProduct.model.dto.*;
import com.hedgehog.admin.exception.AdminProductAddException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminProductServiceImpl implements AdminProductService {

    private final AdminProductMapper mapper;
    public AdminProductServiceImpl(AdminProductMapper mapper) {
        this.mapper = mapper;
    }


//    @Override
//    public List<AdminProductDTO> selectAllProductList() {
//        List<AdminProductDTO> productList = mapper.selectAllProductList();
//        return productList;
//    }



    @Override
    public List<AdminProductDTO> searchProduct(AdminProductForm form) {
        List<AdminProductDTO> productList = mapper.searchProduct(form);

        return productList;
    }

    @Override
    @Transactional
    public void productAdd(AdminProductAddForm product) throws AdminProductAddException {
        log.info("=================================" + product.toString());

        int result = 0;

//        상품테이블 insert
        int addProduct = mapper.addProduct(product);

//        옵션테이블 insert
        int addOption = mapper.addOption(product);

//        옵션리스트 테이블 insert
        int addOptionList = mapper.addOptionList(product);

        List<AttachmentDTO> attachmentList = product.getAttachmentList();

        for(int i = 0; i < attachmentList.size(); i++){
            attachmentList.get(i).setProductCode(product.getProductCode());
        }

        int attachmentResult = 0;
        for(int i = 0; i < attachmentList.size(); i++){
            attachmentResult += mapper.addImg(attachmentList.get(i));
        }


        if (!(addProduct > 0) || !(addOption > 0) || !(attachmentResult > 0) || !(addOptionList > 0)) {
            throw new AdminProductAddException("상품 등록에 실패하셨습니다.");
        }
    }


    @Override
    public List<AdminCategoryDTO> findOptionList(int upperCategoryCode) {
        List<AdminCategoryDTO> findCategory = mapper.searchOption(upperCategoryCode);
        return findCategory;

    }


}
