package com.hedgehog.client.myshop.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WithdrawalReasonMapper {

    void insertWithdrawalReason(int userCode, String summernoteContent);
}
