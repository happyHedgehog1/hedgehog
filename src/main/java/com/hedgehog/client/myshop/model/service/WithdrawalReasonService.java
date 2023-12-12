package com.hedgehog.client.myshop.model.service;

import com.hedgehog.client.myshop.model.dao.WithdrawalReasonMapper;
import org.springframework.stereotype.Service;

@Service
public class WithdrawalReasonService {
    private final WithdrawalReasonMapper withdrawalReasonMapper;

    public WithdrawalReasonService(WithdrawalReasonMapper withdrawalReasonMapper) {
        this.withdrawalReasonMapper = withdrawalReasonMapper;
    }

    public void insertWithdrawalReason(int userCode, String summernoteContent) {
        withdrawalReasonMapper.insertWithdrawalReason(userCode,summernoteContent);
    }
}
