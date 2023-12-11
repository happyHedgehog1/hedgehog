package com.hedgehog.client.myshop.model.service;

import com.hedgehog.client.myshop.model.dao.WithdrawalReasonMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class WithdrawalReasonService {
    private final WithdrawalReasonMapper withdrawalReasonMapper;
    private static final String UPLOAD_DIR = "C:/path/to/upload/directory/";

    public WithdrawalReasonService(WithdrawalReasonMapper withdrawalReasonMapper) {
        this.withdrawalReasonMapper = withdrawalReasonMapper;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;
        String filePath = UPLOAD_DIR + fileName;
        File dest = new File(filePath);
        File path = new File(UPLOAD_DIR+"thumbnail/");
        if(!path.exists()){
            boolean success = dest.mkdirs();
            if(success){
                System.out.println("폴더가 성공적으로 생성됐습니다.");
            }else{
                System.out.println("폴더 생성 중 오류가 발생했습니다.");
            }
        }else{
            System.out.println("폴더가 이미 존재합니다.");
        }
        file.transferTo(dest);
        generateThumbnail(dest);

        withdrawalReasonMapper.saveImageInfo(fileName, filePath, "thumbnail_" + fileName, filePath + "thumbnail/");

        return filePath + "thumbnail/thumbnail_" + fileName;
    }

    private void generateThumbnail(File originalFile) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalFile);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int thumbnailSize = 300;
        int newWidth, newHeight;

        if (width > height) {
            newWidth = thumbnailSize;
            newHeight = (int) Math.round(thumbnailSize * (double) height / width);
        } else {
            newWidth = (int) Math.round(thumbnailSize * (double) width / height);
            newHeight = thumbnailSize;
        }

        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        thumbnail.getGraphics().drawImage(resizedImage, 0, 0, null);

        String thumbnailFileName = "thumbnail_" + originalFile.getName();
        String thumbnailFilePath = UPLOAD_DIR + "thumbnail/" + thumbnailFileName;
        File thumbnailFile = new File(thumbnailFilePath);

        ImageIO.write(thumbnail, "JPEG", thumbnailFile);
    }
}
