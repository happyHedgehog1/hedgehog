package com.hedgehog.admin.adminMember.model.dao;

import com.hedgehog.admin.adminMember.model.dto.*;
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

    AdminCustomerDTO searchMail(int memberCode);

    AdminSendMailDTO searchMailForm(int i);

    int updateWithdrawState(AdminAllMemberDTO adminAllMemberDTO);


//    int insertMailHistoryTable(AdminSendMailDTO mailDTO);
}
