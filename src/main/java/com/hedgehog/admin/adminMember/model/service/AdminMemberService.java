package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;

import java.util.List;

public interface AdminMemberService {
//    List<adminAllMemberDTO> selectAllMemberList();

    List<AdminAllMemberDTO> selectMember(AdminMemberForm form);
}
