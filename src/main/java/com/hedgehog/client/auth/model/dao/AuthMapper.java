package com.hedgehog.client.auth.model.dao;

import com.hedgehog.client.auth.model.dto.LoginUserDTO;
import com.hedgehog.client.auth.model.dto.MailDTO;
import com.hedgehog.client.auth.model.dto.MemberDTO;
import com.hedgehog.client.auth.model.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {
    String selectUserById(String userId);

    String selectMemberByEmail(String email);

    int insertCode(String randomCode);

    int selectLastInsertCertifiedNumber();

    int certifyEmail(int inputCertifiedCode, String certifiedKey);

    int insertUser(MemberDTO newMember);

    Integer selectUserCode();

    int insertAuthorityList(Integer userCode);

    int insertCustomer(Integer userCode, MemberDTO newMember);

    int insertMember(Integer userCode, MemberDTO newMember);

    LoginUserDTO findByUsername(String username);

    int updateConnectionDate(int userCode);

    List<PostDTO> getRegistPosts();

    MailDTO searchMailForm(int formCode);
}
