package com.hedgehog.client.auth.model.service;

import com.hedgehog.client.auth.model.dto.MemberDTO;
import com.hedgehog.client.auth.model.dto.PostDTO;
import com.hedgehog.common.common.exception.UserCertifiedException;
import com.hedgehog.common.common.exception.UserRegistPostException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AuthService {

    /*회원 조회용 메서드. 중복확인 부분*/
    boolean selectUserById(String userId);

    boolean selectMemberByEmail(String email);

    int selectCertifiedNumber(String s) throws UserCertifiedException;

    boolean certifyEmail(int inputCertifiedCode, String certifiedKey);

    /*회원가입용메서드*/
    boolean registMember(MemberDTO newMember);

    UserDetails findByUserId(String username);

    List<PostDTO> getRegistPosts() throws UserRegistPostException;
}
