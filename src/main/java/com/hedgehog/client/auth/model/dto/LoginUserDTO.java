package com.hedgehog.client.auth.model.dto;

import com.hedgehog.common.enums.UserRole;
import lombok.*;

import java.sql.Timestamp;


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

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Timestamp getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(Timestamp connectionDate) {
        this.connectionDate = connectionDate;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getWithdrawState() {
        return withdrawState;
    }

    public void setWithdrawState(String withdrawState) {
        this.withdrawState = withdrawState;
    }

    public String getRole() {
        return role.getRole();
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
