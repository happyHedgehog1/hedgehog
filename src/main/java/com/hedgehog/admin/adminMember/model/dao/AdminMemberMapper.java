package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import com.hedgehog.admin.adminMember.model.dto.AdminSendMailDTO;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
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

    int point(AdminAllMemberDTO adminAllMemberDTO);

    int pointAdd(AdminMemberDTO memberDTO);


    AdminSendMailDTO serachMail(int i);

    AdminSendMailDTO sendMail(int memberId);


//    int insertMailHistoryTable(AdminSendMailDTO mailDTO);
}
