package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.AdminMemberMapper;
import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminMemberServiceImpl implements AdminMemberService {
    private final AdminMemberMapper mapper;

    public AdminMemberServiceImpl(AdminMemberMapper mapper) {
        this.mapper = mapper;
    }



    @Override
    public List<AdminAllMemberDTO> selectMember(AdminMemberForm form){
        List<AdminAllMemberDTO> memberList = mapper.selectMember(form);

    return memberList;
    }


//    @Override
//    public List<adminAllMemberDTO> searchMembers(Map<String, Object> paramMap) {
//        return null;
//    }


}
