package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminInquiryMapper;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminInquiryServiceImpl implements AdminInquiryService {

    private final AdminInquiryMapper mapper;

    public AdminInquiryServiceImpl(AdminInquiryMapper mapper) {this.mapper = mapper;}

    @Override
    public List<AdminInquiryDTO> searchInquiry(AdminInquiryForm form) {
        List<AdminInquiryDTO> inquiryList = mapper.searchInquiry(form);
        return inquiryList;
    }




}
