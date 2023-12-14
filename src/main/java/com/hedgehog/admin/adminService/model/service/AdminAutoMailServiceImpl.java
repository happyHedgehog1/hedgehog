package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminAutoMapper;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.admin.exception.AdminProductAddException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class AdminAutoMailServiceImpl implements AdminAutoMailService{

    private final AdminAutoMapper mapper;

    public AdminAutoMailServiceImpl(AdminAutoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AdminAutoMailDTO previewMail(int mailCode) {
        AdminAutoMailDTO adminAutoMailDTO = mapper.previewMail(mailCode);
        log.info(String.valueOf(adminAutoMailDTO));

        return adminAutoMailDTO;
    }

    @Override
    @Transactional
    public void modifyMail(AdminAutoMailDTO mailDTO) throws AdminProductAddException {
        int result = mapper.modifyMail(mailDTO);

        if(!(result > 0)){
            throw new AdminProductAddException("메일 수정에 실패하였습니다.");
        }

    }


}
