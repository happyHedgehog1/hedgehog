package com.hedgehog.client.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    String selectUserById(String userId);

    String selectMemberByEmail(String email);

    int insertCode(String randomCode);

    int selectLastInsertCertifiedNumber();

    int certifyEmail(int inputCertifiedCode, String certifiedKey);
}
