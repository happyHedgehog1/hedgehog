package com.hedgehog.client.myshop.controller;

import com.hedgehog.client.myshop.model.service.WithdrawalReasonService;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("/myshop/withdrawalReason")
@Slf4j
public class WithdrawalReasonController {
    @Value("img")
    private String IMAGE_DIR;

    @Value("C:/hedgehog/")
    private String ROOT_LOCATION;

    private final WithdrawalReasonService withdrawalReasonService;

    public WithdrawalReasonController(WithdrawalReasonService withdrawalReasonService) {
        this.withdrawalReasonService = withdrawalReasonService;
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> handleImageUpload(@RequestParam("file") MultipartFile file) {
        try {
            String rootLocation = ROOT_LOCATION + IMAGE_DIR;

            String fileUploadDirectory = rootLocation + "/upload/original/";

            File directory = new File(fileUploadDirectory);
            log.info("사진업로드 경로: " + fileUploadDirectory);
            if (!directory.exists()) {
                log.info("폴더 생성: " + directory.mkdirs());
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            log.info("파일 이름 생성: " + fileName);
            String filePath = fileUploadDirectory + fileName;
            log.info("파일 전체 경로 생성: " + filePath);

            file.transferTo(new File(filePath));
            log.info("파일 생성 완료");

            String thumbnailDirectory = rootLocation + "/upload/thumbnail/";
            File directory2 = new File(thumbnailDirectory);
            log.info("압축사진업로드 경로: " + thumbnailDirectory);
            if (!directory2.exists()) {
                log.info("폴더 생성2: " + directory2.mkdirs());
            }

            Thumbnails.of(filePath)
                    .size(600, 400)
                    .toFile(thumbnailDirectory + "/thumbnail_" + fileName);
            log.info("압축 파일 생성 완료");

            return new ResponseEntity<>(filePath, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("이미지 업로드에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
