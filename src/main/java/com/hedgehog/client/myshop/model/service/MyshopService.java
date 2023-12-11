package com.hedgehog.client.myshop.model.service;

import com.hedgehog.client.auth.model.dto.MemberDTO;
import com.hedgehog.client.myshop.model.dao.MyshopMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
