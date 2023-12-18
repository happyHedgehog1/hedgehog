package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminFAQMapper;
import com.hedgehog.admin.adminService.model.dto.AdminFAQDTO;
import com.hedgehog.admin.adminService.model.dto.AdminFAQForm;
import com.hedgehog.admin.exception.BoardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Override
@Transactional
    public void FAQStateUpdate(AdminFAQDTO faqdto) throws BoardException {
    log.info("");

    int result = mapper.FAQStateUpdate(faqdto);
    log.info("===========FAQStateUpdate Result {}", result);

    if (!(result > 0)){
        throw new BoardException("상태 변경에 실패하셨습니다.");
    }
    }

    @Override
    @Transactional
    public void insertFAQ(AdminFAQDTO adminFAQDTO) throws BoardException {

        log.info("");

        int result = mapper.insertFAQ(adminFAQDTO);
        if (!(result > 0)){
            throw new BoardException("상태 변경에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public void insertNotice(AdminFAQDTO adminFAQDTO) throws BoardException {
        log.info("");

        int result = mapper.insertNotice(adminFAQDTO);
        if (!(result > 0)){
            throw new BoardException("상태 변경에 실패하셨습니다.");
        }
    }

    public void noticeStateUpdate(AdminFAQDTO faqdto) throws BoardException {
        log.info("");

        int result = mapper.noticeStateUpdate(faqdto);
        log.info("===========noticeStateUpdate Result {}", result);

        if (!(result > 0)){
            throw new BoardException("상태 변경에 실패하셨습니다.");
        }
    }
}
