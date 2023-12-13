package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.AdminMemberMapper;
import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminMemberForm;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.exception.OrderStateUpdateException;
import com.hedgehog.admin.exception.UnregistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void memberWithdraw(AdminAllMemberDTO adminAllMemberDTO) throws UnregistException {

        log.info("");
        log.info("");

//        user 테이블의 withdraw_state를 Y로 변경
        int result = mapper.updateMemberWithdrawState(adminAllMemberDTO);
        log.info(" memberWithdraw result =================================== ", result);
//        withdraw 테이블에 해당 member insert
        List<AdminAllMemberDTO> memberDTO = mapper.searchMember(adminAllMemberDTO);
        int result1 = mapper.insertWithdrawTable(adminAllMemberDTO);

        if(!(result > 0)) {
            throw new UnregistException("상태 변경에 실패하셨습니다.");
        }


    }

    @Override
    public AdminAllMemberDTO memberDetail(int memberCode) {
        log.info("");
        log.info("");
        log.info("memberDetail -------------------------- 시작~~~~~~~~~");

        AdminAllMemberDTO memberDTO = null;


        memberDTO = mapper.memberDetail(memberCode);
        log.info("memberDetail -------------------------- 끗~~~~~~~~~" + memberDTO);



        return memberDTO;
    }

    @Override
    @Transactional
    public void pointPage(AdminAllMemberDTO adminAllMemberDTO) throws UnregistException {

        log.info("");
        log.info("");


        int result = mapper.point(adminAllMemberDTO);


        if(!(result > 0)) {
            throw new UnregistException("상태 변경에 실패하셨습니다.");
        }


    }

    @Override
    @Transactional
    public void pointAdd(AdminMemberDTO memberDTO) throws UnregistException {
        log.info("");
        log.info("");


        int result = mapper.pointAdd(memberDTO);


        if(!(result > 0)) {
            throw new UnregistException("상태 변경에 실패하셨습니다.");
        }
    }


//    @Override
//    public List<adminAllMemberDTO> searchMembers(Map<String, Object> paramMap) {
//        return null;
//    }


}
