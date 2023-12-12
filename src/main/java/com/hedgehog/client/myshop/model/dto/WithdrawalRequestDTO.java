package com.hedgehog.client.myshop.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WithdrawalRequestDTO {
    private String summernoteContent;
    private String userPwd;
}
