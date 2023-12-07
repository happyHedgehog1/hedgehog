package com.hedgehog.admin.adminManagement.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminDTO {
    private int userCode; // user_code
    private String id; // id
    private String userName; // name. 관리자이름
    private String connectionDate; // 최근 로그인 시간
    private String creationDate; // 계정생성시간
    private String withdrawState; // 계정삭제유무
    private String authorityName; // 계정권한이름(SUPER_ADMIN, ADMIN)
}
