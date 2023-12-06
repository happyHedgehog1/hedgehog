package com.hedgehog.admin.adminProduct.controller;
import com.google.gson.Gson;
import com.hedgehog.admin.adminProduct.model.dto.adminProductDTO;
import com.hedgehog.admin.adminProduct.model.dto.adminProductForm;
import com.hedgehog.admin.adminProduct.model.service.adminProductServiceImpl;
import com.hedgehog.admin.exception.AdminProductAddException;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/product")
@Slf4j
public class adminProductController {

    private final adminProductServiceImpl adminProductServiceImpl;

    public adminProductController(adminProductServiceImpl adminProductService) {
        this.adminProductServiceImpl = adminProductService;
    }

//    @Value("img")
//    private String IMAGE_DIR;
//
//    @Value("C:/hedgehog/")
//    private String ROOT_LOCATION;
//
//    @PostMapping("/thumbnailRegist")
//    private String productImgAdd(@RequestParam("thumbnail") MultipartFile thumbnail,
//                                 @RequestParam("sub_thumbnail") List<MultipartFile> sub_thumbnails,
//                                 @RequestParam("pro_img") MultipartFile pro_img,
//                                 RedirectAttributes rttr)
//    {
//        log.info("===========================섬네일 넣기~~~~~~~~~");
//        String rootLocation = ROOT_LOCATION + IMAGE_DIR;
//
//        String fileUploadDirectory = rootLocation + "/upload/original";
//        String thumbnailDirectory = rootLocation + "/upload/thumbnail";
//
//        File directory = new File(fileUploadDirectory);
//        File directory2 = new File(thumbnailDirectory);
//
//        log.info("=================fileUploadDirectory" + directory);
//        log.info("=================thumbnailDirectory" + directory2);
//
//        if(!directory.exists() || !directory2.exists()){
//
//            log.info("============폴더 생성 : " + directory.mkdirs());
//            log.info("=========== 폴더 생성 : " + directory2.mkdirs());
//
//        }
//
//        List<Map<String, String>> fileList = new ArrayList<>();
//
//        List<MultipartFile> paramFileList = new ArrayList<>();
//        paramFileList.add(thumbnail);
//        log.info("=============대표 사진" + thumbnail);
//
//        for (MultipartFile sub_thumbnail : sub_thumbnails) {
//            paramFileList.add(sub_thumbnail);
//            log.info("=============상품 사진" + sub_thumbnail);
//
//        }
//        paramFileList.add(pro_img);
//        log.info("=============제품 상세 사진" + pro_img);
//
//        for(MultipartFile paramFile : paramFileList){
//            if (paramFile.getSize() > 0){
//                String originFileName = paramFile.getOriginalFilename();
//
//                log.info("================originFileName : " + originFileName);
//
//                String ext = originFileName.substring(originFileName.lastIndexOf("."));
//                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
//
//                log.info("================변경한 이름 " + savedFileName);
//
//                log.info("================paramFile : " + fileUploadDirectory + "/" + savedFileName);
//                    paramFile.transferTo(new File(fileUploadDirectory + "/" + savedFileName));
//
//                    Map<String, String> fileMap = new HashMap<>();
//                    fileMap.put("originFileName", originFileName);
//                    fileMap.put("savedFileName", savedFileName);
//                    fileMap.put("savePath", fileUploadDirectory);
//
//                    int width = 0;
//                    int height = 0;
//
//                    String fieldName = paramFile.getName();
//                    log.info("=================필드 name " + fieldName);
//
//                    if ("thumbnail".equals(fieldName) || "sub_thumbnail".equals(fieldName)) {
//                        fileMap.put("fileType", "thumbnail");
//                        width = 640;
//                        height = 640;
//
//                    } else {
//                        fileMap.put("fileType", "BODY");
//                        width = 860;
//                        height = 7500;
//                    }
//
//                    Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(width, height)
//                            .toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);
//
//                    fileMap.put("thumbnailPath", "/thumbnail_" + savedFileName);
//
//                    fileList.add(fileMap);
//
//                    log.info("===================fileList" + fileList);
//
//
//                }
//            }
//        }
//
//    }

    @PostMapping("/productAdd")
    private String productAdd(@ModelAttribute adminProductDTO product, RedirectAttributes rttr) throws AdminProductAddException {

        log.info("=============productAdd 시작~~~~~~~~~");

        adminProductServiceImpl.productAdd(product);

        rttr.addFlashAttribute("message", "상품 등록에 성공하였습니다.");

        log.info("=============product 끗~~~~~~~~~~~~~~~");
        return "redirect:admin/content/product/productAdd";
    }



    /**
     * 상품 조회하는 메소드
     * @param form html에서 form 데이터로 전달받은 객체를 선언한 DTO
     * @return 조회된 리스트, 총 상품수, 판매중인 상품수, 판매중지 상태인 상품수를 반환
     */
    @GetMapping(value = "/productserach")
    public ModelAndView productsearch(@ModelAttribute adminProductForm form) {
        log.info("productsearch ====================== start");

        log.info(form.toString());

        List<adminProductDTO> productList = adminProductServiceImpl.searchProduct(form);
        log.info("=================================productList" + productList);

        int totalResult = productList.size();
        int countY = 0;
        int countN = 0;
        for (int i = 0; i < productList.size(); i++) {
            char orderableStatus = productList.get(i).getOrderableStatus();
            log.info(String.valueOf(orderableStatus));

            if ('Y' == orderableStatus) {
                countY++;

            }
            if('N' == orderableStatus){
                countN++;
            }

        }
        log.info("=============================countY" + countY);
        log.info("=============================countN" + countN);

        ModelAndView modelAndView = new ModelAndView("admin/content/product/productSerch");
        modelAndView.addObject("productList", productList); // 모델에 productList를 추가
        modelAndView.addObject("totalResult", totalResult);
        modelAndView.addObject("countY", countY);
        modelAndView.addObject("countN", countN);



        log.info("totalResult" + String.valueOf(totalResult));

        return modelAndView;
    }



    /**
     * 상품조회 페이지 연결 메소드
     * @return 관리자 상품조회 페이지
     */
//    @GetMapping("/productserachPage")
//    public String productsearch(){ return "admin/content/product/productSerch";}

    /**
     * 상품등록 페이지 연결 메소드
     * @return 관리자 상품등록 페이지
     */
    @GetMapping("/productAdd")
    public String productadd(){ return "admin/content/product/productAdd";}





}
