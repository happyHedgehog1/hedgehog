package com.hedgehog.admin.adminService.model.service;

import com.hedgehog.admin.adminMember.model.dto.AdminCustomerDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminSendMailDTO;
import com.hedgehog.admin.adminService.model.dao.AdminCommentMapper;
import com.hedgehog.admin.adminService.model.dto.AdminCommentDTO;
import com.hedgehog.admin.exception.BoardException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class AdminCommentServiceImpl implements AdminCommentService {
    private final AdminCommentMapper mapper;
    private final JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "oneinfurniture0@gmail.com";


    public AdminCommentServiceImpl(AdminCommentMapper mapper, JavaMailSender javaMailSender) throws BoardException {
        this.mapper = mapper;
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Transactional
    public void inquiryComment(AdminCommentDTO adminCommentDTO) throws BoardException, MessagingException, UnsupportedEncodingException {
        if ("N".equals(adminCommentDTO.getAnswer_state())) {
            log.info("--------------------------------N");
            log.info("--------------------------------AdminCommentDTO" + adminCommentDTO);

            // "N"이면 댓글 삽입
            int result = mapper.inquiryComment(adminCommentDTO);
            int result2 = mapper.updateState(adminCommentDTO);
            //상품문의 메일발송


            log.info("");

            AdminCustomerDTO customerDTO = mapper.searchMail(adminCommentDTO.getUser_code()); //메일 주소 가져오는 매퍼
            log.info("mailAddress========================" + customerDTO.toString());
            AdminSendMailDTO sendMailDTO = mapper.searchmailForm(8); //메일 양식, 제목 가져오는 매퍼
            log.info("sendMailDTO==================" + sendMailDTO.toString());


                String inquiryTitle = adminCommentDTO.getInqtitle();  //inquiryTitle에 문의 제목 넣기
                String inquiryContent = adminCommentDTO.getInqcontent(); //inquiryContent 문의 내용 넣기
                String comment = adminCommentDTO.getContent(); // comment에 답변 내용 넣기
                String emailContent = sendMailDTO.getContent() //가져온 메일 양식의 내용에 {} 내용을 위에 선언한 String값으로 대체하기
                        .replace("{inquiryTitle}", inquiryTitle)
                        .replace("{inquiryContent}", inquiryContent)
                        .replace("{comment}", comment);

                MimeMessage mimeMileMessage = javaMailSender.createMimeMessage(); //메일 전송을 위한 메소드
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMileMessage, true, "UTF-8");

                mimeMessageHelper.setSubject(MimeUtility.encodeText(sendMailDTO.getTitle(), "UTF-8", "B")); //메일 제목
                mimeMessageHelper.setText(emailContent, true); //메일 내용 emailContent는 위에서 replace한 내용
                mimeMessageHelper.setFrom(FROM_ADDRESS); //보내는 사람 맨위에서 선언
                mimeMessageHelper.setTo(customerDTO.getEmail()); //받는 사람 메일 주소 위에서 mapper로 조회한 값

                mimeMessageHelper.addInline("image", new ClassPathResource("static/admin/images/logo.png"));
                //메일 양식에 있는 로고 이미지 이거 안쓰면 엑박으로 보내짐

                javaMailSender.send(mimeMileMessage);//메일 진짜로 보내는 메소드


                result++;
                log.info("result =================================== {}", result);

            if (result <= 0) {
                throw new BoardException("댓글 등록에 실패하셨습니다.");
            }
        } else if ("Y".equals(adminCommentDTO.getAnswer_state())) {
            // "Y"이면 댓글 업데이트
            log.info("--------------------------------Y");
            log.info("--------------------------------AdminCommentDTO" + adminCommentDTO);


            int result = mapper.inquiryCommentUpdate(adminCommentDTO);
            if (result <= 0) {
                throw new BoardException("댓글 업데이트에 실패하셨습니다.");
            }
        }
    }
}

