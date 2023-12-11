package com.hedgehog.client.myshop.model.dao;

import com.hedgehog.client.auth.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyshopMapper {

    int getMyPoint(int userCode);

    MemberDTO getMemberInfo(int userCode);
}
