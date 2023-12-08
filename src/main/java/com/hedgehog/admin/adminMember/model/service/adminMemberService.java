package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dto.adminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.adminMemberForm;

import java.util.List;
import java.util.Map;

public interface adminMemberService {
//    List<adminAllMemberDTO> selectAllMemberList();

    List<adminAllMemberDTO> selectMember(adminMemberForm form);
}
