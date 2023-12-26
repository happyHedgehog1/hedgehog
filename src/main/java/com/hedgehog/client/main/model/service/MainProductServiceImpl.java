package com.hedgehog.client.main.model.service;

import com.hedgehog.client.main.model.dao.MainProductMapper;
import com.hedgehog.client.main.model.dto.MainProduct;

import java.util.List;

public class MainProductServiceImpl implements MainProductService{


    private final MainProductMapper mapper;

    public MainProductServiceImpl(MainProductMapper mapper) {

        this.mapper = mapper;
    }



}
