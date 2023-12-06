package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.adminMemberMapper;
import com.hedgehog.admin.adminMember.model.dto.adminAllMemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class adminMemberServiceImpl implements adminMemberService{
    private final adminMemberMapper mapper;

    public adminMemberServiceImpl(adminMemberMapper mapper) {
        this.mapper = mapper;
    }
@Override
    public List<adminAllMemberDTO> selectAllMemberList() {
        List<adminAllMemberDTO> memberList = mapper.selectAllMemberList();
        return memberList;
    }
}
