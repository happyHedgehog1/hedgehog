package com.hedgehog.client.myshop.model.service;

import com.hedgehog.client.auth.model.dto.MemberDTO;
import com.hedgehog.client.myshop.model.dao.MyshopMapper;
import com.hedgehog.common.common.exception.UserEmailNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyshopService {
    private final MyshopMapper myshopMapper;

    public MyshopService(MyshopMapper myshopMapper) {
        this.myshopMapper = myshopMapper;
    }

    public int getMyPoint(int userCode) {
        return myshopMapper.getMyPoint(userCode);
    }

    public MemberDTO getMemberInfo(int userCode) {
        return myshopMapper.getMemberInfo(userCode);
    }

    public String getEmail(int userCode) throws UserEmailNotFoundException {
        String email = myshopMapper.getEmail(userCode);
        if (email == null) {
            throw new UserEmailNotFoundException("이메일을 찾지 못했습니다.");
        }
        return email;
    }

    @Transactional
    public boolean modifyMember(int userCode,MemberDTO member) {
        int result1 = myshopMapper.updateUser(member);
        int result2 = myshopMapper.updateCustomer(userCode, member);
        int result3 = myshopMapper.updateMember(userCode, member);
        int result = result1 + result2 + result3;
        return result >= 0 ? true : false;
    }
}
