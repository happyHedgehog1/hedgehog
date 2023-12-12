package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminFAQMapper;
import com.hedgehog.admin.adminService.model.dto.AdminFAQDTO;
import com.hedgehog.admin.adminService.model.dto.AdminFAQForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminFAQServiceImpl implements AdminFAQService {

    private final AdminFAQMapper mapper;

    public AdminFAQServiceImpl(AdminFAQMapper mapper) {this.mapper = mapper;}
@Override
    public  List<AdminFAQDTO> searchFAQ(AdminFAQForm form) {
        List<AdminFAQDTO> FAQList = mapper.searchFAQ(form);
        return FAQList;
    }

    public List<AdminFAQDTO> searchNotice(AdminFAQForm form) {
        List<AdminFAQDTO> noticeList = mapper.searchNotice(form);
        return noticeList;
    }
}
