package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.exception.UnregistException;

import java.util.List;

public interface AdminMemberService {
//    List<adminAllMemberDTO> selectAllMemberList();

    List<AdminAllMemberDTO> selectMember(AdminMemberForm form);

    public void memberWithdraw(AdminAllMemberDTO adminAllMemberDTO) throws UnregistException;

    AdminAllMemberDTO memberDetail(int memberCode);
}
