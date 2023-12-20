package com.hedgehog.common.login.mapper;

import com.hedgehog.common.login.dto.WithdrawStatus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WithdrawMapper {
    WithdrawStatus checkWithdraw(String userId);

    void updateCancelDate(int withdrawCode);
}
