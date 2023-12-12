package com.hedgehog.client.myshop.model.dao;

import com.hedgehog.client.auth.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyshopMapper {

    int getMyPoint(int userCode);

    MemberDTO getMemberInfo(int userCode);

    String getEmail(int userCode);

    int updateUser(MemberDTO member);

    int updateCustomer(int userCode, MemberDTO member);

    int updateMember(int userCode, MemberDTO member);

    int getUserCode(String userId);
}
