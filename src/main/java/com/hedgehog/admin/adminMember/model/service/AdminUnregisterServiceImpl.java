package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.AdminUnregisterMapper;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterForm;
import com.hedgehog.admin.exception.UnregistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AdminUnregisterServiceImpl implements AdminUnregisterService {
    private final AdminUnregisterMapper mapper;

    public AdminUnregisterServiceImpl(AdminUnregisterMapper mapper) {this.mapper = mapper;
    }
@Override
    public List<AdminUnregisterDTO> selectUnregister(AdminUnregisterForm form) {
        List<AdminUnregisterDTO> unregisterList = mapper.selectUnregister(form);
        return unregisterList;
    }

    @Override
    @Transactional
    public void causeUpdate(AdminUnregisterDTO adminUnregisterDTO) throws UnregistException {
        int result = 0;
        log.info("");
        if(adminUnregisterDTO.getState().equals("adminWithdrawal")) {
            mapper.causeUpdate(adminUnregisterDTO);
            result++;
            log.info(" orderState result =================================== ", result);
        }else {
            mapper.withdrawalCancel(adminUnregisterDTO);
            result++;
        }
        if(!(result > 0)) {
            throw new UnregistException(" 수정에 실패하셨습니다.");
        }
    }
}
