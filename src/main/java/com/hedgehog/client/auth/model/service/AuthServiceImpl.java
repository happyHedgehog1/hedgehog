package com.hedgehog.client.auth.model.service;

import com.hedgehog.client.auth.model.dao.AuthMapper;
import com.hedgehog.client.auth.model.dto.UserDTO;
import com.hedgehog.common.common.exception.UserCertifiedException;
import com.hedgehog.common.common.exception.UserRegistException;
import groovy.util.logging.Slf4j;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper mapper;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, AuthMapper mapper) {
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public boolean selectUserById(String userId) {
        String result = mapper.selectUserById(userId);
        return result != null ? true : false; // 아이디가 있으면 true를, 없으면 false를
    }

    @Override
    public boolean selectMemberByEmail(String email) {
        String result = mapper.selectMemberByEmail(email);
        return result != null ? true : false;
    }

    @Override
    @Transactional
    public int selectCertifiedNumber(String randomCode) throws UserCertifiedException {
        /*인증코드 테이블에 값을 생성하고. 다음에 key값을 다시 반환하기.*/
        int result = mapper.insertCode(randomCode);
        if (result <= 0)
            throw new UserCertifiedException("입력에 실패했습니다.");
        int certifiedNumber = mapper.selectLastInsertCertifiedNumber();
        return certifiedNumber;
    }

    @Override
    public boolean certifyEmail(int inputCertifiedCode, String certifiedKey) {
        int successCount = mapper.certifyEmail(inputCertifiedCode, certifiedKey);
        System.out.println(inputCertifiedCode);
        System.out.println(certifiedKey);
        return successCount == 0;
    }

    @Override
    public void registUser(UserDTO user) throws UserRegistException {

    }
}
