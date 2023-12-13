package com.hedgehog.admin.adminProduct.model.dao;

import com.hedgehog.admin.adminProduct.model.dto.*;
import com.hedgehog.admin.exception.AdminProductAddException;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminProductMapper {


//    List<AdminProductDTO> searchProduct(AdminProductForm form);


    //조건에 따른 상품 조회 메소드
    List<AdminProductDTO> searchProduct(AdminProductForm form);
    //상품 등록시 product테이블 insert 메소드
    int addProduct(AdminProductDTO product);
    //상품 등록시 option테이블 insert 메소드, 코드가 없는 경우에만 사용된다.
    int addOption(OptionDTO optionDTO);
    //상품 등록시 이미지 등록 메소드
    int addImg(AttachmentDTO attachmentDTO);
    //ajax 이용하여 동적으로 서브 카테고리 불러오는 메소드
    List<AdminCategoryDTO> searchCategory(int upperCategoryCode);
    //optionList 테이블 insert 메소드
    int addOptionList(OptionListDTO optionListDTO);

    AdminProductDTO selectProductDetail(int productCode);

    int productUpdate(AdminProductDTO product);

    int optionListUpdate(OptionListDTO optionListDTO);

    List<OptionListDTO> searchOptionList(AdminProductDTO product);

    int deleteOptionList(OptionListDTO existingOptions);

    int addOptionList2(OptionListDTO optionListDTO);

    int updateImg(AttachmentDTO attachment);

    List<AdminCategoryDTO> searchCategoryList(AdminCategoryDTO category);

    List<AdminProductDTO> searchCategoryDetail(String categoryCode);
}
