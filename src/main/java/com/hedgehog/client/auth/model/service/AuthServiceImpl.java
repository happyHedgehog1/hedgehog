package com.hedgehog.client.auth.model.service;

import com.hedgehog.admin.exception.UnregistException;
import com.hedgehog.client.auth.model.dao.AuthMapper;
import com.hedgehog.client.auth.model.dto.*;
import com.hedgehog.common.common.exception.UserCertifiedException;
import com.hedgehog.common.common.exception.UserRegistPostException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthMapper mapper;
    private final JavaMailSender javaMailSender;

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
    @Transactional
    public boolean registMember(MemberDTO newMember) throws MessagingException, UnsupportedEncodingException, UnregistException {
        /*
         * 0. tbl_user에 아이디 있는지 검색하기.
         * 1. 아이디가 없으면 tbl_user에 아이디, 비밀번호, 이름 넣기.
         * 사용자구분은 '고객'. 생성날짜는 현재날짜. 결과값
         * 2. tbl_user에서 사용자 코드 들고와서 저장하기.
         * 3. tbl_authority_list에 값 넣기. 권한코드는 3. 사용자 코드는 가져온 값
         * 4. tbl_customer에 이메일이 있는지 검색하기
         * 5. 이메일이 없으면 tbl_customer에 사용자코드, 이메일, 넣기
         * 회원여부는 'Y' 넣기
         * 6. tbl_member 에 사용자코드, 생년월일, 성별, 이메일동의여부 넣기.
         * 적립금은 0, 누적금액은 0        *
         * */
        /*id가 있으면 true*/
        boolean result1 = mapper.selectUserById(newMember.getUserId()) != null ? true : false;
        if (result1) {
            return false; // 아이디가 있으면 생성중단
        }
        boolean result2 = mapper.insertUser(newMember) != 1 ? true : false;
        if (result2) {
            return false; // user를 생성 못했으면 생성중단
        }
        Integer userCode = mapper.selectUserCode();
        if (userCode == null) {
            return false; // userCode를 못찾는다면. 생성중단
        }
        boolean result3 = mapper.insertAuthorityList(userCode) != 1 ? true : false;
        if (result3) {
            return false; // authorityList를 생성못했으면 생성중단
        }
        boolean result4 = mapper.selectMemberByEmail(newMember.getEmail()) != null ? true : false;
        if (result4) {
            return false; // 만약 이메일이 customer에 들어있다면. 생성중단
        }
        boolean result5 = mapper.insertCustomer(userCode, newMember) != 1 ? true : false;
        if (result5) {
            return false; // customer에 데이터를 넣는데 넣은 값이 없으면 생성중단.
        }
        boolean result6 = mapper.insertMember(userCode, newMember) != 1 ? true : false;
        if (result6) {
            return false; // member에 데이터를 넣는데 넣은 값이 없으면 생성중단.
        }

        // 여기까지 오면 메일 보내는 메서드를 만든다.
        sendRegistEmail(newMember.getEmail(), newMember.getUserId());

        return true; // 계정생성 성공할 경우
    }

    private void sendRegistEmail(String email, String userId) throws MessagingException, UnsupportedEncodingException, UnregistException {
        /*기본적인 원리는 다음과 같다.
         * 1. 보내고 싶은 이메일 주소를 가져온다.
         * 2. */
        final String FROM_ADDRESS = "oneinfurniture0@gmail.com";
        int result = 0;
        log.info("");
        RegistMailDTO registMailDTO = mapper.searchMailForm(1); // 가입인사 form 가져오기.
        log.info("registMailDTO++++++++++++++++++++++++++++" + registMailDTO.toString());

        String emailContent = registMailDTO.getContent()
                .replace("{memberId}", userId);

        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "UTF-8");

        mimeMessageHelper.setSubject(MimeUtility.encodeText(registMailDTO.getTitle(), "UTF-8", "B")); //메일 제목 지정
        mimeMessageHelper.setText(emailContent, true); //메일 내용 지정
        mimeMessageHelper.setFrom(FROM_ADDRESS); //보내는 메일 주소 지정
        mimeMessageHelper.setTo(email); //받는 메일 주소 지정

        mimeMessageHelper.addInline("image", new ClassPathResource("static/admin/images/logo.png"));

        javaMailSender.send(mimeMailMessage);

        result++;
        log.info(" orderState result =================================== ", result);
        if (!(result > 0)) {
            throw new UnregistException(" 수정에 실패하셨습니다.");
        }
    }

    @Override
    @Transactional
    public UserDetails findByUserId(String username) {
        LoginUserDTO user = mapper.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("아이디를 잘못입력했습니다.");
        }
        int userCode = user.getUserCode();
        boolean isUpdateConnectionDate = mapper.updateConnectionDate(userCode) == 1;
        if (!isUpdateConnectionDate) {
            // 데이터가 못들어간 경우
            throw new InternalAuthenticationServiceException("현재 아이디의 connection_date의 값을 넣지 못했습니다.");
        }
        UserDetails login = new LoginDetails(user);

        return login;
    }

    @Override
    public List<PostDTO> getRegistPosts() throws UserRegistPostException {
        List<PostDTO> postList = mapper.getRegistPosts();
        if (postList.size() != 2) {
            throw new UserRegistPostException("개인정보처리방침 또는 이용약관을 가져오지 못했습니다.");
        }
        return postList;
    }
}
