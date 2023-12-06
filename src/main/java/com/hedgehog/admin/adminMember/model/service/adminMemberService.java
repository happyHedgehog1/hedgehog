package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dto.adminAllMemberDTO;

import java.util.List;

public interface adminMemberService {
    List<adminAllMemberDTO> selectAllMemberList();
}
