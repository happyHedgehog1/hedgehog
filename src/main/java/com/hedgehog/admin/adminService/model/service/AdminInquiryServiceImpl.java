package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminInquiryMapper;
import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryForm;
import com.hedgehog.admin.exception.BoardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminInquiryServiceImpl implements AdminInquiryService {

    private final AdminInquiryMapper mapper;

    public AdminInquiryServiceImpl(AdminInquiryMapper mapper) {this.mapper = mapper;}

    //상품문의 상태변경
    @Override
    public List<AdminInquiryDTO> searchInquiry(AdminInquiryForm form) {
        List<AdminInquiryDTO> inquiryList = mapper.searchInquiry(form);
        return inquiryList;
    }

    @Override
    @Transactional
    public void inqStateUpdate(AdminInquiryDTO inquiryDTO) throws BoardException {
        log.info("");

        int result = mapper.inqStateUpdate(inquiryDTO);
        log.info("===========inqStateUpdate Result {}", result);

        if (!(result > 0)){
            throw new BoardException("상태 변경에 실패하셨습니다.");
        }
    }
    @Override
    public AdminInquiryDTO inquiryDetail(int inquiryCode) {
    log.info("");
    log.info("");
    log.info("inquiryDetail -------------------------- 시작~~~~~~~~~");

    AdminInquiryDTO adminInquiryDTO = null;

    adminInquiryDTO = mapper.inquiryDetail(inquiryCode);
    log.info("inquiryDetail =========" + adminInquiryDTO);

    return adminInquiryDTO;
    }

}
