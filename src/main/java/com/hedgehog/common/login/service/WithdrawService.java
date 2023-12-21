package com.hedgehog.common.login.service;

import com.hedgehog.common.login.dto.WithdrawStatus;
import com.hedgehog.common.login.mapper.WithdrawMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class WithdrawService {
    WithdrawMapper withdrawMapper;

    @Transactional
    public int checkWithdrawStatus(String userId) {
        // 여기서 tbl_withdraw의 상태를 반환함.
        WithdrawStatus withdrawStatus = withdrawMapper.checkWithdraw(userId);
        if (withdrawStatus == null || withdrawStatus.getCancelDate() != null) {
            return 0;
        }
        if (withdrawStatus.getState().equals("Y")) {
            return -1;
        } else {
            log.info(withdrawStatus.toString());
            withdrawMapper.updateCancelDate(withdrawStatus.getWithdrawCode());
            return 1;
        }
    }
}
