package com.hedgehog.common.login.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WithdrawStatus {
    private int withdrawCode;
    private int userCode;
    private Timestamp applyDate;
    private String state;
    private String cause;
    private Timestamp commitDate;
    private Timestamp cancelDate;
}
