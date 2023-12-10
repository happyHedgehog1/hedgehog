package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.adminUnregisterMapper;
import com.hedgehog.admin.adminMember.model.dto.adminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.adminUnregisterForm;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class adminUnregisterServiceImpl implements adminUnregisterService{
    private final adminUnregisterMapper mapper;

    public adminUnregisterServiceImpl(adminUnregisterMapper mapper) {this.mapper = mapper;
    }
@Override
    public List<adminUnregisterDTO> selectUnregister(adminUnregisterForm form) {
        List<adminUnregisterDTO> unregisterList = mapper.selectUnregister(form);
        return unregisterList;
    }
}
