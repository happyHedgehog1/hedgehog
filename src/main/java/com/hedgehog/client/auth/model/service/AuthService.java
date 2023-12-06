package com.hedgehog.client.auth.model.service;

import com.hedgehog.client.auth.model.dto.UserDTO;
import com.hedgehog.common.common.exception.UserCertifiedException;
import com.hedgehog.common.common.exception.UserRegistException;

public interface AuthService {
    /*회원 조회용 메서드. 중복확인 부분*/
    boolean selectUserById(String userId);

    boolean selectMemberByEmail(String email);
    /*회원 가입용 메서드*/
    void registUser(UserDTO user) throws UserRegistException;

    int selectCertifiedNumber(String s) throws UserCertifiedException;

    boolean certifyEmail(int inputCertifiedCode, String certifiedKey);
}
