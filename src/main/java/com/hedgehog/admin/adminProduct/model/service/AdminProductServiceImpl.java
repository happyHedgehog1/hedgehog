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


    /**
     * 상품 조회 메소드
     * @param form
     * @return
     */
    @Override
    public List<AdminProductDTO> searchProduct(AdminProductForm form) {
        List<AdminProductDTO> productList = mapper.searchProduct(form);

        return productList;
    }

    @Override
    @Transactional
    public void productAdd(AdminProductDTO product) throws AdminProductAddException {
        log.info("=================================" + product.toString());

        int result = 0;

//        상품테이블 insert
        int addProduct = mapper.addProduct(product);

//        옵션테이블에 같은 옵션 코드가 없을때만 insert한다

        int addOption = mapper.addOption(product.getOptionDTO());

//        옵션리스트 테이블 insert
        int addOptionList = mapper.addOptionList(product.getOption());

        List<AttachmentDTO> attachmentList = product.getAttachment();

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

    @Override
    public OptionDTO searchOption(OptionDTO optionDTO) {
        OptionDTO resultOptionCode = mapper.searchOptionCode(optionDTO);
        if (optionDTO != null) {
            return resultOptionCode;
        } else {
            return new OptionDTO();
        }
    }

    @Override
    @Transactional
    public void productAddExcludeOptionCode(AdminProductDTO product) throws AdminProductAddException {
        log.info("=================================" + product.toString());

        int result = 0;

//        상품테이블 insert
        int addProduct = mapper.addProduct(product);

        log.info("==========================================> product : {}", product);
        product.getOption().setProductCode(product.getProductCode());

//        옵션리스트 테이블 insert
        int addOptionList = mapper.addOptionList(product.getOption());

        List<AttachmentDTO> attachmentList = product.getAttachment();

        for(int i = 0; i < attachmentList.size(); i++){
            attachmentList.get(i).setProductCode(product.getProductCode());
            log.info("==========================================> product : {}", product);

        }

        int attachmentResult = 0;
        for(int i = 0; i < attachmentList.size(); i++){
            attachmentResult += mapper.addImg(attachmentList.get(i));
        }


        if (!(addProduct > 0) || !(attachmentResult > 0) || !(addOptionList > 0)) {
            throw new AdminProductAddException("상품 등록에 실패하셨습니다.");
        }
    }

    @Override
    public AdminProductDTO selectProductDetail(int productCode) {
        log.info("");
        log.info("");
        log.info("selectProductDetail -------------------------- 시작~~~~~~~~~");

        AdminProductDTO productDTO = null;


            productDTO = mapper.selectProductDetail(productCode);



return productDTO;
    }


}
