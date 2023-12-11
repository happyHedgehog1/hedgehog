package com.hedgehog.client.myshop.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WithdrawalReasonMapper {
    void saveImageInfo(String fileName,
                       String filePath,
                       String thumbnailFileName,
                       String thumbnailFilePath);
}
