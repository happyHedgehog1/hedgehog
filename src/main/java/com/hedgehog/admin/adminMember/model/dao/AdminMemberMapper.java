package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMemberMapper {


    List<AdminAllMemberDTO> selectMember(AdminMemberForm form);

    List<AdminAllMemberDTO> selectAllMemberList();


    int updateMemberWithdrawState(AdminAllMemberDTO adminAllMemberDTO);

    int insertWithdrawTable(AdminAllMemberDTO adminAllMemberDTO);

    List<AdminAllMemberDTO> searchMember(AdminAllMemberDTO adminAllMemberDTO);


    AdminAllMemberDTO memberDetail(int memberCode);
}
