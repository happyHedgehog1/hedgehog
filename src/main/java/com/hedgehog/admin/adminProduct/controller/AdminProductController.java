package com.hedgehog.admin.adminProduct.controller;
import com.hedgehog.admin.adminProduct.model.dto.*;
import com.hedgehog.admin.adminProduct.model.service.AdminProductServiceImpl;
import com.hedgehog.admin.exception.AdminProductAddException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/product")
@Slf4j
public class AdminProductController {

    private final AdminProductServiceImpl adminProductServiceImpl;

    public AdminProductController(AdminProductServiceImpl adminProductService) {
        this.adminProductServiceImpl = adminProductService;
    }

    @Value("img")
    private String IMAGE_DIR;

    @Value("C:/images/")
    private String ROOT_LOCATION;

    @PostMapping("/productAdd")
    private String productAdd(@ModelAttribute AdminProductDTO product,
                              @ModelAttribute OptionDTO option,
                              @ModelAttribute OptionListDTO optionList,
                              @ModelAttribute AdminCategoryDTO categoryDTO,
                              @RequestParam("thumbnail") MultipartFile thumbnail,
                              @RequestParam("sub_thumbnail") List<MultipartFile> sub_thumbnails,
                              @RequestParam("proImg") MultipartFile proImg,
                              RedirectAttributes rttr) throws AdminProductAddException {




        log.info("********************=============productAdd 시작~~~~~~~~~");
        log.info("==========product" + product);
        log.info("==========option" + option);
        log.info("==========optionList" + optionList);
        log.info("==========categoryDTO" + categoryDTO);
        log.info("==========thumbnail" + thumbnail);
        log.info("==========sub_thumbnail" + sub_thumbnails);
        log.info("==========proImg" + proImg);

        adminProductServiceImpl.productAdd(product);

        rttr.addFlashAttribute("message", "상품 등록에 성공하였습니다.");

        log.info("=============product 끗~~~~~~~~~~~~~~~");
        return "redirect:admin/content/product/productAdd";
    }


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





    /**
     * 상품 조회하는 메소드
     * @param form html에서 form 데이터로 전달받은 객체를 선언한 DTO
     * @return 조회된 리스트, 총 상품수, 판매중인 상품수, 판매중지 상태인 상품수를 반환
     */
    @GetMapping(value = "/productserach")
    public ModelAndView productsearch(@ModelAttribute AdminProductForm form) {
        log.info("productsearch ====================== start");

        log.info(form.toString());

        List<AdminProductDTO> productList = adminProductServiceImpl.searchProduct(form);
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
     * ajax 이용 동적 select 메소드
     * @return 선택한 상위 카테고리의 하위 카테고리 리스트들
     */
    @GetMapping("/productAdd")
    public String productadd(){ return "admin/content/product/productAdd";}

    @GetMapping(value = "/category/{upperCategoryCode}", produces = "application/json; charset=UTF-8" )
    @ResponseBody
    public List<AdminCategoryDTO> getCateogoryList(HttpServletResponse res, @PathVariable("upperCategoryCode") int upperCategoryCode) throws IOException {
        log.info("*************************" + upperCategoryCode);

        List<AdminCategoryDTO> categoryList = adminProductServiceImpl.findOptionList(upperCategoryCode);
        log.info("******************" + categoryList);


        return categoryList;
    }





}
