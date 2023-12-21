package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminMember.model.dto.AdminCustomerDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminSendMailDTO;
import com.hedgehog.admin.adminService.model.dao.AdminInquiryMapper;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryDTO;
import com.hedgehog.admin.adminService.model.dto.AdminInquiryForm;
import com.hedgehog.admin.exception.BoardException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AdminInquiryServiceImpl implements AdminInquiryService {

    private final AdminInquiryMapper mapper;
    private final JavaMailSender javaMailSender;

    private static final String FROM_ADDRESS = "oneinfurniture0@gmail.com";

    public AdminInquiryServiceImpl(AdminInquiryMapper mapper, JavaMailSender javaMailSender) {this.mapper = mapper;
        this.javaMailSender = javaMailSender;
    }
//    //상품문의 메일발송
//    @Override
//    @Transactional
//    public void inqMail(AdminInquiryDTO adminInquiryDTO) throws BoardException, MessagingException, UnsupportedEncodingException{
//        int result = 0;
//        log.info("");
//
//            result = mapper.inqMail(adminInquiryDTO);
//            AdminCustomerDTO customerDTO = mapper.searchMail(adminInquiryDTO.getMember_code());
//            log.info("mailAddress========================" + customerDTO.toString());
//            AdminSendMailDTO sendMailDTO = mapper.searchmailForm(8);
//            log.info("sendMailDTO==================" + sendMailDTO.toString());
//
//
//            String inquiryTitle = adminInquiryDTO.getTitle();
//            String inquiryContent = adminInquiryDTO.getContent();
//            String comment = adminInquiryDTO.getComContent();
//            String emailContent = sendMailDTO.getContent()
//                    .replace("{inquiryTitle}", inquiryTitle)
//                    .replace("{inquiryContent}", inquiryContent)
//                    .replace("{comment}", comment);
//
//            MimeMessage mimeMileMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMileMessage, true, "UTF-8");
//
//            mimeMessageHelper.setSubject(MimeUtility.encodeText(sendMailDTO.getTitle(), "UTF-8", "B"));
//            mimeMessageHelper.setText(emailContent, true);
//            mimeMessageHelper.setFrom(FROM_ADDRESS);
//            mimeMessageHelper.setTo(customerDTO.getEmail());
//
//            mimeMessageHelper.addInline("image", new ClassPathResource("static/admin/images/logo.png"));
//
//            javaMailSender.send(mimeMileMessage);
//
//
//            result++;
//        log.info("result =================================== {}", result);
//        }
//
//





    //상품문의 상태변경
    @Override
    public List<AdminInquiryDTO> searchInquiry(AdminInquiryForm form) {
        List<AdminInquiryDTO> inquiryList = mapper.searchInquiry(form);
        return inquiryList;
    }

    @Override
    @Transactional
    public void inqStateUpdate(AdminInquiryDTO inquiryDTO) throws BoardException {
        log.info("");

        int result = mapper.inqStateUpdate(inquiryDTO);
        log.info("===========inqStateUpdate Result {}", result);

        if (!(result > 0)){
            throw new BoardException("상태 변경에 실패하셨습니다.");
        }
    }
    @Override
    public AdminInquiryDTO inquiryDetail(int inquiryCode) {
    log.info("");
    log.info("");
    log.info("inquiryDetail -------------------------- 시작~~~~~~~~~");

    AdminInquiryDTO adminInquiryDTO = null;

    adminInquiryDTO = mapper.inquiryDetail(inquiryCode);
    log.info("inquiryDetail =========" + adminInquiryDTO);

    return adminInquiryDTO;
    }


}
