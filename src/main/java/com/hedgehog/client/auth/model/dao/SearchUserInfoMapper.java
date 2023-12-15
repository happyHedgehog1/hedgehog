package com.hedgehog.client.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchUserInfoMapper {
    Integer selectMemberByEmail(String email);

    void updateCertificationNumber(Integer certificationCode, String randomStr);

    int certifyEmail(int inputCertifiedCode, String certifiedKey);

    String findUserId(String email, int emailAuthenticationNumber, int hiddenCertifiedKey);

    Integer selectMemberByUserIdAndEmail(String userId, String email);

    Integer findUser(String userId, String email, int emailAuthenticationNumber, int hiddenCertifiedKey);

    void insertNewUserPassword(Integer userCode, String encodePassword);
}
