package com.hedgehog.admin.adminProduct.model.service;

import com.hedgehog.admin.adminProduct.model.dao.AdminProductMapper;
import com.hedgehog.admin.adminProduct.model.dto.*;
import com.hedgehog.admin.exception.AdminProductAddException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;

@Service
@Slf4j
public class AdminProductServiceImpl implements AdminProductService {

    private final AdminProductMapper mapper;
    public AdminProductServiceImpl(AdminProductMapper mapper) {
        this.mapper = mapper;
    }


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

    /**
     * 상품 등록 메소드
     * @param product
     * @throws AdminProductAddException
     */
    @Override
    @Transactional
    public void productAdd(AdminProductDTO product) throws AdminProductAddException {
        log.info("=================================" + product.toString());


//        상품테이블 insert
        int addProduct = mapper.addProduct(product);
        log.info("===================addProduct :{}", addProduct);


        int addOptionResult = 0;
//        옵션테이블에 insert
        for(int i = 0; i < product.getOptionDTO().size(); i++) {
            OptionDTO optionDTO = product.getOptionDTO().get(i);
            int addOption = mapper.addOption(optionDTO);
            addOptionResult += addOption;

        }
        log.info("===================addOptionResult :{}", addOptionResult);

        int productCode = product.getProductCode();
        List<OptionListDTO> optionListDTO = product.getOptionList();
        log.info("productCode:      ====   "   + productCode);

        int addOptionListResult = 0;
//        옵션리스트 테이블 insert
        for(int i = 0; i < product.getOptionDTO().size(); i++) {
            optionListDTO.get(i).setProductCode(productCode);
            log.info("productCode:         "   + optionListDTO.get(i).getProductCode());
            optionListDTO.get(i).setOptionCode(product.getOptionDTO().get(i).getOptionCode());
            log.info("optioncode:         "   + optionListDTO.get(i).getOptionCode());
            optionListDTO.get(i).setStock(product.getOptionList().get(i).getStock());
            int addOptionList = mapper.addOptionList(optionListDTO.get(i));
            addOptionListResult += addOptionList;

        }
        log.info("===================addProduct :{}", addProduct);

        List<AttachmentDTO> attachmentList = product.getAttachment();

        for (int i = 0; i < attachmentList.size(); i++) {
            attachmentList.get(i).setProductCode(product.getProductCode());
        }

        int attachmentResult = 0;
        for (int i = 0; i < attachmentList.size(); i++) {
            attachmentResult += mapper.addImg(attachmentList.get(i));
        }


        if (!(addProduct > 0) && !(addOptionResult > 0) && !(attachmentResult > 0) && !(addOptionListResult > 0)) {

                throw new AdminProductAddException("상품 등록에 실패하셨습니다.");
            }
        }

    /**
     * ajax 이용 동적 카테고리 불러오는 메소드
     * @param upperCategoryCode
     * @return
     */
    @Override
    public List<AdminCategoryDTO> findCategoryList(int upperCategoryCode) {
        List<AdminCategoryDTO> findCategory = mapper.searchCategory(upperCategoryCode);
        return findCategory;

    }

    /**
     * 상품 수정시 상품 정보 productModify 페이지로 정보 뿌려주는 메소드
     * @param productCode
     * @return
     */
    @Override
    public AdminProductDTO selectProductDetail(int productCode) {
        log.info("");
        log.info("");
        log.info("selectProductDetail -------------------------- 시작~~~~~~~~~");

        AdminProductDTO productDTO = null;


        productDTO = mapper.selectProductDetail(productCode);
        log.info("selectProductDetail -------------------------- 끗~~~~~~~~~" + productDTO);



        return productDTO;
    }

    @Override
    @Transactional
    public void productUpdate(AdminProductDTO product) throws AdminProductAddException {
        log.info("");
        log.info("");
        log.info("producUpdate -------------------------- 시작~~~~~~~~~");
//        상품 테이블 update
        int updateProduct = mapper.productUpdate(product);

//        옵션 테이블 update
        int updateOption = 0;
//        옵션테이블에 insert
        for(int i = 0; i < product.getOptionDTO().size(); i++) {
            OptionDTO optionDTO = product.getOptionDTO().get(i);
            optionDTO.setOptionCode(product.getOptionList().get(i).getOptionCode());
            int addOption = mapper.addOption(optionDTO);
            updateOption += addOption;}
        List<OptionListDTO> optionListDTO = product.getOptionList();

        log.info("----------------------optionListDTO 됨~~~~~~~~" + optionListDTO);


        for(int i = 0; i< optionListDTO.size(); i++){
                log.info("********************** optionListDTO.get(i).getOptionCode()~~~~~~~~~~~~" + optionListDTO.get(i).getOptionCode());
                optionListDTO.get(i).setProductCode(product.getProductCode());

                    int addOptionList = mapper.addOptionList2(optionListDTO.get(i));

                }




//        사진테이블 update
        for (AttachmentDTO attachment : product.getAttachment()) {
            attachment.setProductCode(product.getProductCode());
            if (!attachment.getOriginalName().isEmpty()) {
                int updateImg = mapper.updateImg(attachment);

            }
        }

        if(!(updateProduct > 0)|| !(updateOption > 0)){
            throw new AdminProductAddException("상품 수정에 실패하였습니다.");
        }


        log.info("selectProductDetail -------------------------- 끗~~~~~~~~~");


    }


}
