package com.hedgehog.client.board.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hedgehog.client.auth.model.dto.LoginDetails;
import com.hedgehog.client.board.model.dto.UploadedImageDTO;
import com.hedgehog.client.board.model.dto.UploadedImageListDTO;
import com.hedgehog.client.board.model.service.BoardWriteService;
import com.hedgehog.client.orderDetails.model.dto.OrderDetailsCollect;
import com.hedgehog.client.orderDetails.model.dto.OrderDetailsDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardWriteController {
    private final BoardWriteService boardWriteService;
    private final ObjectMapper objectMapper;

    public BoardWriteController(BoardWriteService boardWriteService, ObjectMapper objectMapper) {
        this.boardWriteService = boardWriteService;
        this.objectMapper = objectMapper;
    }

    @Value("img")
    private String IMAGE_DIR;

    @Value("C:/hedgehog/")
    private String ROOT_LOCATION;

    @GetMapping("/writeQuestion")
    public ModelAndView writeQuestion(ModelAndView mv) {
        mv.setViewName("/client/content/board/writeQuestion");
        return mv;
    }

    @PostMapping("/writeQuestion")
    public String questionRegist(@AuthenticationPrincipal LoginDetails loginDetails,
                                 @RequestParam String option,
                                 @RequestParam(required = false) String orderNumber,
                                 @RequestParam(required = false) String productName,
                                 @RequestParam String inputTitle,
                                 @RequestParam String editordata,
                                 @RequestParam String uploadedImages,
                                 RedirectAttributes redirectAttributes) {
        int userCode = loginDetails.getLoginUserDTO().getUserCode();
        log.info("현재 로그인한 계정 코드... : " + userCode);
        log.info("");
        log.info("");
        log.info("내가 입력한 option ... : " + option);
        log.info("내가 입력한 orderNumber ... : " + orderNumber);
        log.info("내가 입력한 productName ... : " + productName);
        log.info("내가 입력한 inputTitle ... : " + inputTitle);
        log.info("내가 입력한 summernote ...");
        log.info(editordata);
        log.info("내가 입력한 uploadedImages ...");
        log.info(uploadedImages);
        String newEditorData = "<p>주문번호 : " + orderNumber + "</p><br><p>제품이름 : " + productName + "</p><br>" + editordata;
        // 주문번호와 제품이름과 원래 summernote의 데이터를 합쳐서 전달한다.
        try {
            List<UploadedImageDTO> uploadedImageList = objectMapper.readValue(uploadedImages, new TypeReference<List<UploadedImageDTO>>() {
            });
            log.info("이제 JSON으로 고친 값...");
            for (UploadedImageDTO image : uploadedImageList) {
                log.info("Convert Path: " + image.getConvertPath());
                log.info("Save Path: " + image.getSavePath());
                log.info("Source Name: " + image.getSourceName());
                log.info("Convert Name: " + image.getConvertName());
            }

            // 1. 현재 로그인한 계정의 정보
            // 2. insert tbl_inquiry
            // 3. 게시글의 번호 가져오기
            // 4. insert tbl_post_img .. 이 네가지가 트랜잭션 하나이므로. Service를 부른다.
            boolean isSuccess = boardWriteService.questionRegist(userCode, option, inputTitle, newEditorData, uploadedImageList);

            if (!isSuccess) {
                redirectAttributes.addFlashAttribute("message", "알 수 없는 오류가 발생했습니다. 메인화면으로 나갑니다.");
                return "redirect:/";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "알 수 없는 오류가 발생했습니다. 메인화면으로 나갑니다.");
            return "redirect:/";
        }
        return "redirect:/board/questionList";
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

    @GetMapping("/writeReview")
    public String writeReview(@AuthenticationPrincipal LoginDetails loginDetails,
                              @RequestParam int orderDetailsCode,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (loginDetails == null) {
            redirectAttributes.addFlashAttribute("message", "잘못된 접근입니다. 메인으로 돌아갑니다.");
            return ("redirect:/");
        }
        log.info("orderDetailsCode ... 잘 왔나 : " + orderDetailsCode);
        // orderDetailsCode 를 이용해서.
        // 1. 내 계정이 맞는가를 찾아야 한다.
        String userId = loginDetails.getUsername();
        log.info("현재 로그인한 아이디.... : " + userId);
        String myId = boardWriteService.findMyIdByOrderDetailsCode(orderDetailsCode);
        if (!myId.equals(userId)) {
            redirectAttributes.addFlashAttribute("message", "리뷰하려는 제품상세와 계정정보가 일치하지 않습니다. \n메인으로 돌아갑니다.");
            return ("redirect:/");
        }

        OrderDetailsDTO orderDetailsDTO = boardWriteService.selectOrderDetail(orderDetailsCode);
        model.addAttribute("orderDetailsDTO", orderDetailsDTO);
        return "/client/content/board/writeReview";
    }

    @PostMapping("/writeReview")
    public String reviewRegist(@AuthenticationPrincipal LoginDetails loginDetails,
                               @RequestParam String editordata,
                               @RequestParam String uploadedImages,
                               @RequestParam String orderDetailsCode,
                               @RequestParam String stars,
                               RedirectAttributes redirectAttributes) {
        int userCode = loginDetails.getLoginUserDTO().getUserCode();
        log.info("현재 로그인한 계정 코드... : " + userCode);
        log.info("");
        log.info("");
        log.info("내가 입력한 summernote ...");
        log.info(editordata);
        log.info("내가 입력한 uploadedImages ...");
        log.info(uploadedImages);
        log.info("내가 입력한 orderDetailsCode ...");
        log.info(orderDetailsCode);
        log.info("내가 입력한 stars ...");
        log.info(stars);
        try {
            List<UploadedImageDTO> uploadedImageList = objectMapper.readValue(uploadedImages, new TypeReference<>() {
            });
            log.info("이제 JSON으로 고친 값...");
            for (UploadedImageDTO image : uploadedImageList) {
                log.info("Convert Path: " + image.getConvertPath());
                log.info("Save Path: " + image.getSavePath());
                log.info("Source Name: " + image.getSourceName());
                log.info("Convert Name: " + image.getConvertName());
            }
            // 1. 현재 로그인한 계정의 정보
            // 2. insert tbl_review
            // 3. 게시글의 번호 가져오기
            // 4. insert tbl_post_img ..
            // 5. update tbl_order_details ..
            // 6. select and update tbl_member -> point 목적
            // 7. update tbl_product ...
            String userId = loginDetails.getUsername();
            log.info("현재 로그인한 아이디.... : " + userId);
            String myId = boardWriteService.findMyIdByOrderDetailsCode(Integer.parseInt(orderDetailsCode));
            if (!myId.equals(userId)) {
                redirectAttributes.addFlashAttribute("message", "리뷰하려는 제품상세와 계정정보가 일치하지 않습니다. \n메인으로 돌아갑니다.");
                return ("redirect:/");
            }

            OrderDetailsDTO orderDetailsDTO = boardWriteService.selectOrderDetail(Integer.parseInt(orderDetailsCode));
            boolean isSuccess = boardWriteService.reviewRegist(userCode, editordata, orderDetailsDTO, stars, uploadedImageList);

            if (!isSuccess) {
                redirectAttributes.addFlashAttribute("message", "알 수 없는 오류가 발생했습니다. 메인화면으로 나갑니다.");
                return "redirect:/";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "알 수 없는 오류가 발생했습니다. 메인화면으로 나갑니다.");
            return "redirect:/";
        }
        return "redirect:/board/reviewList";
    }
}
