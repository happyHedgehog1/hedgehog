package com.hedgehog.admin.adminMember.model.service;

import com.hedgehog.admin.adminMember.model.dao.AdminUnregisterMapper;
import com.hedgehog.admin.adminMember.model.dto.AdminCustomerDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminSendMailDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUnregisterForm;
import com.hedgehog.admin.exception.UnregistException;
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
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AdminUnregisterServiceImpl implements AdminUnregisterService {
    private final AdminUnregisterMapper mapper;
    private final JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "oneinfurniture0@gmail.com";


    public AdminUnregisterServiceImpl(AdminUnregisterMapper mapper, JavaMailSender javaMailSender) {
        this.mapper = mapper;
        this.javaMailSender = javaMailSender;
    }
@Override
    public List<AdminUnregisterDTO> selectUnregister(AdminUnregisterForm form) {
        List<AdminUnregisterDTO> unregisterList = mapper.selectUnregister(form);
        return unregisterList;
    }

    /**
     * 탈퇴 승인이면 탈퇴 사유와 탈퇴 완료일 now()로 update, tbl_withdraw에 해당 회원 정보 insert 하고
     * 해당 회원에게 탈퇴 승인 메일 발송
     * 탈퇴 취소면 탈퇴신청취소일 now()로 update, tbl_withdraw에 해당 회원정보 삭제
     * @param adminUnregisterDTO
     * @throws UnregistException
     */
    @Override
    @Transactional
    public void causeUpdate(AdminUnregisterDTO adminUnregisterDTO) throws UnregistException, MessagingException, UnsupportedEncodingException {
        int result = 0;
        log.info("");
        if(adminUnregisterDTO.getState().equals("adminWithdrawal")) {
//            탈퇴 승인 관련 테이블 업데이트, insert mapper
            result = mapper.causeUpdate(adminUnregisterDTO);
//            탈퇴 승인 메일 보내기
//            user_code를 기준으로 해당 회원 메일 주소와 id 가져오는 매퍼
            AdminCustomerDTO customerDTO = mapper.searchMail(adminUnregisterDTO.getUser_code());
            log.info("mailAddress++++++++++++++++++++++++++++"+customerDTO.toString());
//            탈퇴 승인 메일양식 가져오는 매퍼
            AdminSendMailDTO sendMailDTO = mapper.searchMailForm(2);
            log.info("sendMailDTO++++++++++++++++++++++++++++"+sendMailDTO.toString());
//            탈퇴 승인 메일 보내기

            LocalDate unregisterDate = LocalDate.now();
            String id = customerDTO.getId();

            String emailContent = sendMailDTO.getContent()
                    .replace("{unregisterDate}",  unregisterDate.toString()  )
                    .replace("{memberId}",  id );

            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "UTF-8");

            mimeMessageHelper.setSubject(MimeUtility.encodeText(sendMailDTO.getTitle(), "UTF-8", "B")); //메일 제목 지정
            mimeMessageHelper.setText(emailContent, true); //메일 내용 지정
            mimeMessageHelper.setFrom(FROM_ADDRESS); //보내는 메일 주소 지정
            mimeMessageHelper.setTo(customerDTO.getEmail()); //받는 메일 주소 지정

            mimeMessageHelper.addInline("image", new ClassPathResource("static/admin/images/logo.png"));

            javaMailSender.send(mimeMailMessage);


            result++;
            log.info(" orderState result =================================== ", result);
        }else {
//            탈퇴취소일때 작동하는 메소드
//            tbl_withdraw의 state를 N으로 변경
            mapper.withdrawalCancel(adminUnregisterDTO);
//            tbl_user의 state를 N으로 변경
            mapper.userTableStateUpdate(adminUnregisterDTO);
            result++;
        }
        if(!(result > 0)) {
            throw new UnregistException(" 수정에 실패하셨습니다.");
        }
    }

    /**
     * 탈퇴 회원 정보 상세페이지와 연결되는 메소드
     * @param userCode
     * @return
     */
    @Override
    public AdminUnregisterDTO unregisterDetail(int userCode) {
        log.info("");
        log.info("");
        log.info("unregisterDetail -------------------------- 시작~~~~~~~~~");

        AdminUnregisterDTO adminUnregisterDTO = null;

        adminUnregisterDTO = mapper.unregisterDetail(userCode);
        log.info("orderDetail -------------------------- 끗~~~~~~~~~" + adminUnregisterDTO);



        return adminUnregisterDTO;    }


//    @Scheduled(fixedRate = 5000) // 5초마다 실행
//    public void myScheduledTask() {
//        log.info("스케쥴러 작동~~~~~~~~~~~~~~~~");
//    }


}
