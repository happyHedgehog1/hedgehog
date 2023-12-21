package com.hedgehog.client.auth.model.service;

import com.hedgehog.client.auth.model.dao.SearchUserInfoMapper;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class SearchUserInfoService {
    private final SearchUserInfoMapper mapper;
    private final AuthServiceImpl authService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Integer selectMemberByEmail(String email) throws MessagingException, UnsupportedEncodingException {
        Integer certificationCode = mapper.selectMemberByEmail(email);
        log.info("인증코드 PK... >> " + certificationCode);
        // email과 member 여부를 통해서 인증코드 PK를 가져온다.
        // 일반적으로 회원가입할때 certificationNumber가 생성되기 때문에. 있어야만 한다.

        if (certificationCode == null) {
            return certificationCode;
        }
        // 여기서부터는 null 이 아니라 실제 계정이 있는 경우

        int min = 100000;
        int max = 1000000;
        String randomCode = String.valueOf(new Random().nextInt(max - min) + min);
        log.info("새롭게 만들어진 랜덤인증번호... : " + randomCode);
        mapper.updateCertificationNumber(certificationCode, randomCode);
        boolean result = authService.sendCheckEmailMail(email,randomCode);

        if(result==false){
            return -1;
        }



        return certificationCode; // 이번에는 인증코드 그 자체가 필요하다.
    }

    public boolean certifyEmail(int inputCertifiedCode, String certifiedKey) {
        /*inputCertifiedCode 는 내가 직접 입력한 인증번호
         * certifiedKey 는 서버에 저장되어 있는 PK*/
        boolean isPass = mapper.certifyEmail(inputCertifiedCode, certifiedKey) == 1 ? true : false;
        log.info("인증번호 인증이 완료 됐나... : " + isPass);
        return isPass;
    }

    public String findUserId(String email, int emailAuthenticationNumber, int hiddenCertifiedKey) {
        /*위의 세가지 정보로 아이디를 가져온다.*/
        String userId = mapper.findUserId(email, emailAuthenticationNumber, hiddenCertifiedKey);
        log.info("아이디 가져왔나... : " + userId);
        return userId;
    }

    @Transactional
    public Integer selectMemberByUserIdAndEmail(String userId, String email) throws MessagingException, UnsupportedEncodingException {
        Integer certificationCode = mapper.selectMemberByUserIdAndEmail(userId, email);
        log.info("인증코드 PK... >> " + certificationCode);
        // email과 member 여부를 통해서 인증코드 PK를 가져온다.
        // 일반적으로 회원가입할때 certificationNumber가 생성되기 때문에. 있어야만 한다.

        if (certificationCode == null) {
            return certificationCode;
        }
        // 여기서부터는 null 이 아니라 실제 계정이 있는 경우

        int min = 100000;
        int max = 1000000;
        String randomCode = String.valueOf(new Random().nextInt(max - min) + min);
        log.info("새롭게 만들어진 랜덤인증번호... : " + randomCode);
        mapper.updateCertificationNumber(certificationCode, randomCode);
        boolean result = authService.sendCheckEmailMail(email,randomCode);

        if(result==false){
            return -1;
        }
        return certificationCode; // 이번에는 인증코드 그 자체가 필요하다.
    }

    @Transactional
    public String insertUserPassword(String userId, String email, int emailAuthenticationNumber, int hiddenCertifiedKey) throws MessagingException, UnsupportedEncodingException {
        /*위의 네가지 정보로 값이 가져와지는지 확인한다.*/
        Integer userCode = mapper.findUser(userId, email, emailAuthenticationNumber, hiddenCertifiedKey);
        log.info("아이디 가져왔나... : " + userCode);
        String newUserPassword = null;
        if (userCode != null) {
            newUserPassword = generateRandomPassword();
            log.info("새로운 비밀번호... : " + newUserPassword);

            mapper.insertNewUserPassword(userCode,passwordEncoder.encode(newUserPassword));

            // 여기서 메일 전송
            boolean result = authService.sendPasswordMail(email,newUserPassword);
            if(!result){
                return "sendMiss";
            }

        }
        return newUserPassword;
    }

    public String generateRandomPassword() {
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digitChars = "0123456789";
        String specialChars = "!@#$%^&*";

        String allChars = lowercaseChars + uppercaseChars + digitChars + specialChars;

        SecureRandom random = new SecureRandom();
        int passwordLength = 12;

        StringBuilder passwordBuilder = new StringBuilder();

        passwordBuilder.append(getRandomChar(lowercaseChars, random));
        passwordBuilder.append(getRandomChar(uppercaseChars, random));
        passwordBuilder.append(getRandomChar(digitChars, random));
        passwordBuilder.append(getRandomChar(specialChars, random));
        for (int i = 4; i < passwordLength; i++) {
            passwordBuilder.append(getRandomChar(allChars, random));
        }
        return passwordBuilder.toString();
    }

    private char getRandomChar(String source, SecureRandom random) {
        int randomIndex = random.nextInt(source.length());
        return source.charAt(randomIndex);
    }
}
