package com.hedgehog.admin.adminEvent.model.service;

import com.hedgehog.admin.adminEvent.model.dao.AdminEventMapper;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventForm;
import com.hedgehog.admin.adminEvent.model.dto.AdminEventDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminEventServiceImpl implements AdminEventService{
    private final AdminEventMapper mapper;

    public AdminEventServiceImpl(AdminEventMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<AdminEventDTO> searchEventList(AdminEventForm form) {
        List<AdminEventDTO> eventList = mapper.searchEventList(form);
        return eventList;
    }

    @Override
    public List<AdminEventDTO> eventDetail(int postCode) {
        List<AdminEventDTO> eventDTO = mapper.eventDetail(postCode);
        return eventDTO;
    }

    @Override
    public List<AdminProductDTO> searchProduct(AdminEventForm form) {
        List<AdminProductDTO> productDTO = mapper.searchProduct(form);
        return productDTO;
    }

    @Override
    @Transactional
    public void updateEventStatus(AdminEventForm form) {
        log.info("");
        log.info("");
        //tbl_product의 각 제품의 event_progressionstatus를 state의 값으로 변경

        for(int i = 0; i < form.getAllProductCodes().size(); i++) {
            int productCode = Integer.parseInt(form.getAllProductCodes().get(i));
            int result = mapper.updateEventProgressionStatus(productCode);
            log.info("--------------------productCode"+productCode);

        }
        log.info("-------------------form" + form);

//        tbl_event 테이블에 insert
        int result2 = mapper.insertEventTable(form);
        int postCode = form.getPost_code();
        form.setPost_code(postCode);
//        tbl_event_product_list 테이블에 상품코드 insert
        log.info("-------------------postCode" + postCode);

        for(int i = 0; i < form.getAllProductCodes().size(); i++) {
            int productCode = Integer.parseInt(form.getAllProductCodes().get(i));
            form.setPost_code(postCode);
            form.setSearchEndPrice(productCode);
            log.info("-------------------postCode" + postCode);
            int result3 = mapper.insertEventProductListTable(form);
        }

    }

    @Override
    @Transactional
    public void modifyEvent(AdminEventForm form) {
        //tbl_product의 각 제품의 event_progressionstatus를 전부 N으로 변경
        for(int i = 0; i < form.getProductCode().size(); i++) {
            int productCode = Integer.parseInt(form.getProductCode().get(i));
            int result = mapper.updateEventProgressionStatusN(productCode);
        }
        //새로 추가된 제품만 event_progressionstatus를 state값으로 변경
        for(int i = 0; i < form.getAllProductCodes().size(); i++) {
            int productCode = Integer.parseInt(form.getAllProductCodes().get(i));
            int result1 = mapper.updateEventProgressionStatus(productCode);
            log.info("--------------------productCode"+productCode);
        }

        int result2 = mapper.updateEventTable(form);

        int result3 = mapper.deleteEventProductList(form);

        for(int i =0; i < form.getAllProductCodes().size(); i++){
            int productCode = Integer.parseInt(form.getAllProductCodes().get(i));
            form.setSearchEndPrice(productCode);

            int result4 = mapper.insertEventProductListTable(form);
        }



    }


}






