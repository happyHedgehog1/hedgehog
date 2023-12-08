package com.hedgehog.client.auth.model.dto;

import com.hedgehog.common.enums.UserRole;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter@ToString
public class LoginUserDTO {
    // 사용자 테이블
    private int userCode;
    private String userId;
    private String userPwd;
    private String name;
    private String classify;
    private Timestamp connectionDate;
    private Timestamp creationDate;
    private String withdrawState;

    // 권한리스트 테이블과 권한목록 테이블
    private UserRole role; // 권한목록 부분의 권한명

}
