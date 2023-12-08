//package com.hedgehog.client.list.controller;
//import com.hedgehog.client.list.model.dto.ListDTO;
//import com.hedgehog.client.list.model.service.ListServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/list")
//@Slf4j
//public class ListController {
//
//    @Value("${image.image-dir}")
//    private String IMAGE_DIR;
//
//    @Value("${spring.servlet.multipart.location}")
//    private String ROOT_LOCATION;
//
//    private final ListServiceImpl listServiceImpl;
//
//    public ListController(ListServiceImpl listServiceImpl) {
//        this.listServiceImpl = listServiceImpl;
//    }
//
//    @GetMapping("/list")
//    public ModelAndView selectCategoryList(ModelAndView mv) {
//
//        log.info("");
//        log.info("");
//        log.info("[ListController] ========================== start");
//
//        List<ListDTO> category = listServiceImpl.selectCategoryList();
//
//        log.info("category : " + category);
//
//        mv.addObject("category", category);
//        mv.setViewName("client/list/category");
//
//        log.info("[ListController] ========================== end");
//
//        return mv;
//
//    }
//
//
//
//    @GetMapping("/category")
//    public String getCategoryLocation(@RequestParam String type, Model model) {
//
//        log.info("[ListController] getCategoryLocation type : {}", type);
//
//        String subject1 = "";
//        String subject2 = "";
//
//        switch (type) {
//            case "bed": subject1 = "침실";
//                        subject2 = "침대";
//                        break;
//        }
//
//        model.addAttribute("subject1", subject1);
//        model.addAttribute("subject2", subject2);
//
//
//
//        return "category";
//
//    }
//     데이터베이스에서 필요정보를 객체로 받아서 결과페이지로 넘겨준다.
//
//    @GetMapping("/category")
//    public String getCategory(){
//        return "client/content/list/category";
//    }
//
//
//}
