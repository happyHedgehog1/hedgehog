package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import com.hedgehog.admin.adminMember.model.dto.AdminSendMailDTO;
import com.hedgehog.admin.exception.UnregistException;

import java.util.List;

public interface AdminMemberService {
//    List<adminAllMemberDTO> selectAllMemberList();

    List<AdminAllMemberDTO> selectMember(AdminMemberForm form);

    public void memberWithdraw(AdminAllMemberDTO adminAllMemberDTO) throws UnregistException;

    AdminAllMemberDTO memberDetail(int memberCode);

    public void pointPage(AdminAllMemberDTO adminAllMemberDTO) throws UnregistException;

    public void pointAdd(AdminMemberDTO memberDTO) throws UnregistException;


    AdminSendMailDTO selectMemberSendMailPage(int i);

    public void sendMail(AdminSendMailDTO mailDTO);

//    public void insertMailHistoryTable(AdminSendMailDTO mailDTO) throws UnregistException;
}
