package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.adminMemberMapper;
import com.hedgehog.admin.adminMember.model.dto.adminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.adminMemberForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class adminMemberServiceImpl implements adminMemberService{
    private final adminMemberMapper mapper;

    public adminMemberServiceImpl(adminMemberMapper mapper) {
        this.mapper = mapper;
    }



    @Override
    public List<adminAllMemberDTO> selectMember(adminMemberForm form){
        List<adminAllMemberDTO> memberList = mapper.selectMember(form);

    return memberList;
    }


//    @Override
//    public List<adminAllMemberDTO> searchMembers(Map<String, Object> paramMap) {
//        return null;
//    }


}
