package com.hedgehog.admin.adminManagement.model.service;

import com.hedgehog.admin.adminManagement.model.dao.AdminManagementMapper;
import com.hedgehog.admin.adminManagement.model.dto.AdminDTO;
import com.hedgehog.admin.adminManagement.model.dto.AdminRegistrationForm;
import com.hedgehog.admin.adminManagement.model.dto.ChangePwdForm;
import com.hedgehog.client.auth.model.dao.AuthMapper;
import com.hedgehog.common.paging.adminManagementPaging.AdminManagementSelectCriteria;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminManagementService {
    private final AdminManagementMapper mapper;
    private final AuthMapper authMapper;


    public int selectTotalCountAdminInfo() {
        int result = mapper.selectTotalCountAdminInfo();
        return result;
    }
    public List<AdminDTO> getAdminList(AdminManagementSelectCriteria adminManagementSelectCriteria) {
        List<AdminDTO> adminList = mapper.getAdminList(adminManagementSelectCriteria);
        return adminList;
    }

    @Transactional
    public boolean registAdmin(AdminRegistrationForm registrationForm) {
        /*
         * 변경사항. @ModelAttribute로 전달받은
         * 1. tbl_user 아이디 중복체크
         * 없으면
         * 2. insert tbl_user
         * 3. last_insert_id 로 pk값 가져오기.
         * 4. insert tbl_authority_list
         * insert 두가지 다 괜찮으면 true 반환.
         * 위의 세개중 하나라도 어려우면 false 반환. 마찬가지 insert 실패하면 @Transactional이 반응함
         * */
        boolean result1 = authMapper.selectUserById(registrationForm.getAdminAddId())!=null?false:true;
        if(!result1){
            // 아이디가 있으면 -> 일반적으로는 없다고 나올 것이다.
            return false;
        }
        boolean result2 = mapper.insertAdmin(registrationForm)==1;
        if(!result2){
            //성공적으로 넣지 못했으면
            return false;
        }
        Integer userCode = authMapper.selectUserCode();
        if(userCode==null){
            // 선택해오지 못했을 경우
            return false;
        }
        boolean result4 = mapper.insertAuthorityList(userCode)==1;
        if(!result4){
            // 성공적으로 권한을 넣지 못하면.
            return false;
        }
        return true;
    }

    @Transactional
    public boolean deleteAdmin(int userCode) {
        // withdraw 테이블에 값 넣기
        boolean success1 = mapper.insertWithdrawTbl(userCode)==1;
        if(!success1){
            return false;
        }
        boolean success2 =  mapper.updateAdminWithdraw(userCode)==1;
        if(!success2){
            return false;
        }
        return true;
    }

    public boolean updatePwd(ChangePwdForm newPwdForm) {
        boolean success = mapper.updateAdminPwd(newPwdForm)==1;
        return success;
    }

}
