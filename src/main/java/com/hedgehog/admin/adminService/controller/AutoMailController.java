package com.hedgehog.admin.adminService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminService.model.dao.AdminAutoMapper;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailDTO;
import com.hedgehog.admin.adminService.model.dto.AdminAutoMailForm;
import com.hedgehog.admin.adminService.model.service.AdminAutoMailServiceImpl;
import com.hedgehog.admin.exception.AdminProductAddException;
import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/autoMailModify")
public class AutoMailController {

    private final AdminAutoMailServiceImpl autoMail;
    private final ObjectMapper objectMapper;

    public AutoMailController(AdminAutoMailServiceImpl autoMail, ObjectMapper objectMapper) {
        this.autoMail = autoMail;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/emailDetail")
    public String emailDetail(@RequestParam int mailCode, Model model){
        log.info("");
        log.info("");
        log.info("selectProductDetail~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~orderCode : {}", mailCode);

        AdminAutoMailDTO mailDTO = autoMail.emailDetail(mailCode);
        log.info("~~~~~~~~~~~~~~~~emailDetail : {}", mailDTO);


        model.addAttribute("orderDetail", mailDTO);
        return "admin/content/Service/emailDetail";
    }

    @GetMapping(value = "/searchEmailHistory")
    public ModelAndView searchEmailHistory(@ModelAttribute AdminAutoMailForm form){
        log.info("searchEmailHistory============= start");
        log.info(form.toString());

        List<AdminAutoMailDTO> mailDTOList = autoMail.searchEmailHistory(form);
        log.info("searchEmailHistory============= : " + mailDTOList);

        int totalResult = mailDTOList.size();
        log.info("=============================totalResult : " + totalResult);

        ModelAndView mv = new ModelAndView();
        mv.addObject("mailList", mailDTOList);
        mv.setViewName("admin/content/Service/emailHistory");

        return mv;

    }

    @Value("img")
    private String IMAGE_DIR;

    @Value("C:/hedgehog/")
    private String ROOT_LOCATION;

    @PostMapping(value = "/mainSend")
    public String mainSend(@RequestParam String uploadedImages,
                           @RequestParam String title,
                           @RequestParam String summernote,
                           @RequestParam String sendDate,
                           @RequestParam String chooseMember, RedirectAttributes rttr) throws JsonProcessingException, MessagingException, UnsupportedEncodingException {

        log.info("메일보내기 시작~~~~~~~~~~~~~");
        log.info("uploadedImages~~~~~~~~~~~~~" + uploadedImages);
        log.info("title~~~~~~~~~~~~~" +title);
        log.info("summernote~~~~~~~~~~~~~" + summernote);
        log.info("sendDate~~~~~~~~~~~~~" +sendDate);
        log.info("chooseMember~~~~~~~~~~~~~" + chooseMember);
        List<UploadedImageDTO> uploadedImageList = objectMapper.readValue(uploadedImages, new TypeReference<List<UploadedImageDTO>>() {
        });
        log.info("이제 JSON으로 고친 값...");
        for (UploadedImageDTO image : uploadedImageList) {
            log.info("Convert Path: " + image.getConvertPath());
            log.info("Save Path: " + image.getSavePath());
            log.info("Source Name: " + image.getSourceName());
            log.info("Convert Name: " + image.getConvertName());
        }

        boolean isSucces = autoMail.sendMail(uploadedImageList, title, summernote, sendDate, chooseMember);

        return "redirect: /Service/email";
    }


    @PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public Map<String, String> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
        log.info("");
        log.info("");
        log.info("[BoardWriteController] uploadSummernoteImageFile =====... start");
        String rootLocation = ROOT_LOCATION + IMAGE_DIR;
        // C:/hedgehog/img

        String fileUploadDirectory = rootLocation + "/upload/original";
        String thumnailDirectory = rootLocation + "/upload/thumbnail";

        File directory = new File(fileUploadDirectory);
        File directory2 = new File(thumnailDirectory);

        log.info("~~~~~~~~~~~~~~~~~~~~~fileUploadDirectory" + fileUploadDirectory);
        log.info("****************************thumnailDirectory" + thumnailDirectory);

        if (!directory.exists() || !directory2.exists()) {
            log.info("*************************** 폴더 생성" + directory.mkdirs());
            log.info("*************************** 폴더 생성2" + directory2.mkdirs());
        }

        Map<String, String> returnMap = new HashMap<>();
        String originalFileName = multipartFile.getOriginalFilename(); // source_name에 저장됨
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // source_name 에서 확장자를 가져옴
        String convertName = UUID.randomUUID().toString().replace("-", "") + ext; // convert_name. 새롭게 만든 파일이름
        log.info("원본 파일 명... 올릴당시... source_name : " + originalFileName);
        log.info("변환 파일 명... convert_name : " + convertName);
        File originalFile = new File(fileUploadDirectory + "/" + convertName);
        log.info("드라이브에 저장된 경로... : " + fileUploadDirectory + "/" + convertName);

        String convertPath = "/thumbnail_" + convertName;
        log.info("변환파일 경로(실제로 사용하는 사진) : " + convertPath);
        log.info("변환파일 저장 경로 : " + thumnailDirectory + convertPath);
        File convertFile = new File(thumnailDirectory + convertPath);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, originalFile);

            int maxWidth = 640;
            int maxHeight = 640;

            Thumbnails.Builder<File> thumbnailBuilder = Thumbnails.of(originalFile);

            int originalWidth = (int) ImageIO.read(originalFile).getWidth();
            int originalHeight = (int) ImageIO.read(originalFile).getHeight();
            log.info("원본 사진의 너비... : " + originalWidth);
            log.info("원본 사진의 높이... : " + originalHeight);

            if (originalWidth <= maxWidth && originalHeight <= maxHeight) {
                thumbnailBuilder.size(originalWidth, originalHeight);
            } else {
                thumbnailBuilder.size(maxWidth, maxHeight).keepAspectRatio(true);
            }

            thumbnailBuilder
                    .toFile(convertFile);

            returnMap.put("convertPath", convertPath);
            returnMap.put("savePath", fileUploadDirectory);
            returnMap.put("sourceName", originalFileName);
            returnMap.put("convertName", convertName);
            returnMap.put("url", "/thumbPath" + convertPath);
            returnMap.put("responseCode", "success");
        } catch (IOException e) {
            FileUtils.deleteQuietly(originalFile);
            FileUtils.deleteQuietly(convertFile);
            e.printStackTrace();
        }

        log.info("[BoardWriteController] uploadSummernoteImageFile ======== return \n {}", returnMap);
        log.info("[BoardWriteController] uploadSummernoteImageFile ======== end");

        return returnMap;
    }



    @GetMapping("/previewMail")
    public String previewMail(@RequestParam int mailCode, Model model){
        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~mailCode : {}", mailCode);

        AdminAutoMailDTO mailDTO = autoMail.previewMail(mailCode);

        model.addAttribute(mailDTO);


        return "admin/content/Service/mailViewport";

    }

    /**
     * 메일 수정하는 페이지에 기존에 DB에 있는 내용 불러오는 메소드     *
     * @param mailCode
     * @param model
     * @return
     */
    @GetMapping("/modifyMailPage")
    public String modifyMailPage(@RequestParam int mailCode, Model model){
        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~mailCode : {}", mailCode);

        AdminAutoMailDTO mailDTO = autoMail.previewMail(mailCode);

        model.addAttribute(mailDTO);


        return "admin/content/Service/mailModify";

    }

    /**
     * 메일 수정하는 메소드
     * @param mailDTO
     * @return
     * @throws AdminProductAddException
     */
    @PostMapping("/modifyMail")
    public String modifyMail(@ModelAttribute AdminAutoMailDTO mailDTO,
                             RedirectAttributes rttr) throws AdminProductAddException {

        log.info("");
        log.info("");
        log.info("mailModify~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~시작");
        log.info("~~~~~~~~~~~~~~~~mailDTO : {}", mailDTO);

        autoMail.modifyMail(mailDTO);

        rttr.addFlashAttribute("message", "양식 변경에 성공하였습니다.");



        return "redirect:/autoMailModify/modifyMailPage?mailCode=" + mailDTO.getFormCode();

    }

}
