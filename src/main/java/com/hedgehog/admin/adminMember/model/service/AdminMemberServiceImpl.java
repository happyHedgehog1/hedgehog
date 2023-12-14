package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.AdminMemberMapper;
import com.hedgehog.admin.adminMember.model.dto.*;
import com.hedgehog.admin.exception.UnregistException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@Slf4j
public class AdminMemberServiceImpl implements AdminMemberService {
    private final AdminMemberMapper mapper;
    private JavaMailSenderImpl javaMailSender;

    public AdminMemberServiceImpl(AdminMemberMapper mapper, JavaMailSender mailSender) {
        this.mapper = mapper;
        this.mailSender = mailSender;
    }

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "oneinfurniture0@gmail.com";



    @Override
    public List<AdminAllMemberDTO> selectMember(AdminMemberForm form){
        List<AdminAllMemberDTO> memberList = mapper.selectMember(form);

    return memberList;
    }

    @Override
    @Transactional
    public void memberWithdraw(AdminAllMemberDTO adminAllMemberDTO) throws UnregistException {

        log.info("");
        log.info("");

//        user 테이블의 withdraw_state를 Y로 변경
        int result = mapper.updateMemberWithdrawState(adminAllMemberDTO);
        log.info(" memberWithdraw result =================================== ", result);
//        withdraw 테이블에 해당 member insert
        List<AdminAllMemberDTO> memberDTO = mapper.searchMember(adminAllMemberDTO);
        int result1 = mapper.insertWithdrawTable(adminAllMemberDTO);

        if(!(result > 0)) {
            throw new UnregistException("상태 변경에 실패하셨습니다.");
        }


    }

    @Override
    public AdminAllMemberDTO memberDetail(int memberCode) {
        log.info("");
        log.info("");
        log.info("memberDetail -------------------------- 시작~~~~~~~~~");

        AdminAllMemberDTO memberDTO = null;


        memberDTO = mapper.memberDetail(memberCode);
        log.info("memberDetail -------------------------- 끗~~~~~~~~~" + memberDTO);



        return memberDTO;
    }

    @Override
    @Transactional
    public void pointPage(AdminAllMemberDTO adminAllMemberDTO) throws UnregistException {

        log.info("");
        log.info("");


        int result = mapper.point(adminAllMemberDTO);


        if(!(result > 0)) {
            throw new UnregistException("상태 변경에 실패하셨습니다.");
        }


    }

    @Override
    @Transactional
    public void pointAdd(AdminMemberDTO memberDTO) throws UnregistException {
        log.info("");
        log.info("");


        int result = mapper.pointAdd(memberDTO);


        if(!(result > 0)) {
            throw new UnregistException("상태 변경에 실패하셨습니다.");
        }
    }

    @Override
    public AdminSendMailDTO selectMemberSendMailPage(int i) {
        log.info("");
        log.info("");

        AdminSendMailDTO sendMailDTO = mapper.serachMail(i);
        return sendMailDTO;
    }

    @Override
    public void sendMail(AdminSendMailDTO mailDTO) {


        try {
//      customer 테이블에서 customer_code를 기준으로 메일 주소를 가져온다
        List<String> mailAddress = mapper.searchMail(mailDTO.getMemberId());
        log.info(mailAddress.toString());
//        가져온 메일 주소로 메일을 보낸다
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "UTF-8");
            mimeMessageHelper.setSubject(MimeUtility.encodeText(mailDTO.getTitle(), "UTF-8", "B")); //B는 Base64 encoding 메일 깨짐 방지용
            mimeMessageHelper.setText(mailDTO.getContent(), true); //html 형식 사용하면 true 아니면 false
            mimeMessageHelper.setFrom(FROM_ADDRESS);
            mimeMessageHelper.setTo(mailAddress.get(0));
            if(!CollectionUtils.isEmpty(mailDTO.getAttachFileList())) {
                for(AtchFileDto attachFileDto: mailDTO.getAttachFileList()) {
                    FileSystemResource fileSystemResource = new FileSystemResource(new File(attachFileDto.getRealFileNm()));
                    mimeMessageHelper.addAttachment(MimeUtility.encodeText(attachFileDto.getAttachFileNm(), "UTF-8", "B"), fileSystemResource);
                }
            }
        javaMailSender.send(mimeMailMessage);
            log.info("성공~~~~~~~~~~~~~~~~~~~~~~");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            log.info("실패~~~~~~~~~~~~~~~~~~~~~~");

        }

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("noyeonji43@naver.com"); //받는 사람 주소
//        message.setFrom(FROM_ADDRESS);  //보내는 사람 주소 세팅 안하면 yml 파일에 있는 username으로 셋팅
//        message.setSubject(mailDTO.getTitle()); //메일 제목
//        message.setText(mailDTO.getContent());      //메일 내용

//        mailSender.send(message); //실제 메일 발송
    }


//    @Override
//    public List<adminAllMemberDTO> searchMembers(Map<String, Object> paramMap) {
//        return null;
//    }


}
