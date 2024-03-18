package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminService.model.dao.AdminAutoMapper;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailForm;
import com.hedgehog.admin.exception.AdminProductAddException;
import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminAutoMailServiceImpl implements AdminAutoMailService{

    private final AdminAutoMapper mapper;
    private final JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "oneinfurniture0@gmail.com";

    public AdminAutoMailServiceImpl(AdminAutoMapper mapper, JavaMailSender javaMailSender) {
        this.mapper = mapper;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public AdminAutoMailDTO previewMail(int mailCode) {
        AdminAutoMailDTO adminAutoMailDTO = mapper.previewMail(mailCode);
        log.info(String.valueOf(adminAutoMailDTO));

        return adminAutoMailDTO;
    }

    @Override
    @Transactional
    public void modifyMail(AdminAutoMailDTO mailDTO) throws AdminProductAddException {
        int result = mapper.modifyMail(mailDTO);

        if(!(result > 0)){
            throw new AdminProductAddException("메일 수정에 실패하였습니다.");
        }

    }

    @Override
    @Transactional
    public boolean sendMail(List<UploadedImageDTO> uploadedImageList, String title, String summernote, String chooseMember) throws MessagingException, UnsupportedEncodingException {
        String[] searchEmailList = mapper.searchEmailList(chooseMember);
        log.info(searchEmailList.toString());
//        tbl_mail_history에 먼저 insert하고 mail_code를 가져온다
        AdminAutoMailDTO adminAutoMailDTO = new AdminAutoMailDTO();
        adminAutoMailDTO.setTitle(title);
        adminAutoMailDTO.setContent(summernote);
        int result = mapper.insertMailHistory(adminAutoMailDTO);

        log.info(adminAutoMailDTO.toString());
        int mailCode = adminAutoMailDTO.getMail_code();
//        이미지 테이블에 업로드
        int result2 = mapper.imgInsert(uploadedImageList, mailCode);

        if(result2 != 1){
            return false;
        }
//        메일보내기
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "UTF-8");

        mimeMessageHelper.setSubject(MimeUtility.encodeText(adminAutoMailDTO.getTitle(), "UTF-8", "B")); //메일 제목 지정
        mimeMessageHelper.setText(adminAutoMailDTO.getContent(), true); //메일 내용 지정
        mimeMessageHelper.setFrom(FROM_ADDRESS); //보내는 메일 주소 지정
        mimeMessageHelper.setTo(searchEmailList); //받는 메일 주소 지정
        mimeMessageHelper.setBcc(searchEmailList);

        javaMailSender.send(mimeMailMessage);

        return true;
    }

    @Override
    public List<AdminAutoMailDTO> searchEmailHistory(AdminAutoMailForm form) {

        List<AdminAutoMailDTO> mailList = mapper.searchEmailHistory(form);

        return mailList;
    }

    @Override
    public AdminAutoMailDTO emailDetail(int mailCode) {
        log.info("emailDetail 시작~~~~~~~~~~~~~~~~~~");

        AdminAutoMailDTO mailList = mapper.emailDetail(mailCode);
        log.info("emailDetail 시작~~~~~~~~~~~~~~~~~~" + mailList);


        return mailList;
    }

    @Override
    public boolean sendMailOnlyString(String title, String summernote, String chooseMember) throws MessagingException, UnsupportedEncodingException {

//        마케팅 수신 동의한 메일주소랑 유저코드 가져오기
        String[] searchEmailList = mapper.searchEmailList(chooseMember);
        log.info(searchEmailList.toString());
//        tbl_mail_history에 먼저 insert하고 mail_code를 가져온다
        AdminAutoMailDTO adminAutoMailDTO = new AdminAutoMailDTO();
        adminAutoMailDTO.setTitle(title);
        adminAutoMailDTO.setContent(summernote);



        int result = mapper.insertMailHistory(adminAutoMailDTO);

        log.info(adminAutoMailDTO.toString());
        int mailCode = adminAutoMailDTO.getMail_code();

//        이미지 테이블에 업로드
//        adminAutoMailDTO.setEventCode();

//        메일보내기

        log.info("메일 보내기 시작~~~~~~~~~~~~~~~~~~");
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "UTF-8");

        mimeMessageHelper.setSubject(MimeUtility.encodeText(adminAutoMailDTO.getTitle(), "UTF-8", "B")); //메일 제목 지정
        mimeMessageHelper.setText(adminAutoMailDTO.getContent(), true); //메일 내용 지정
        mimeMessageHelper.setFrom(FROM_ADDRESS); //보내는 메일 주소 지정
        mimeMessageHelper.setTo(searchEmailList); //받는 메일 주소 지정
        mimeMessageHelper.setBcc(searchEmailList);


        javaMailSender.send(mimeMailMessage);
        log.info("메일 보내기 끗~~~~~~~~~~~~~~~~~~");


        return true;
    }
}



