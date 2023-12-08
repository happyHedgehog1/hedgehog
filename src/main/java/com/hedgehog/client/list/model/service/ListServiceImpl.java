//package com.hedgehog.client.list.model.service;
//
//import com.hedgehog.client.list.model.dao.ListMapper;
//import com.hedgehog.client.list.model.dto.ListDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class ListServiceImpl implements ListService {
//    private final ListMapper mapper;
//
//
//    public ListServiceImpl(ListMapper mapper) {
//        this.mapper = mapper;
//    }
//
//    public List<ListDTO> selectCategoryList() {
//
//        List<ListDTO> category = mapper.selectCategory();
//
//        log.info("");
//        log.info("");
//        log.info("[BoardServiceImpl]  selectAllThumbnailList ===================== {}", category);
//
//        return category;
//    }
//
//}
