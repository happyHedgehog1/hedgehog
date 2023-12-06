package com.hedgehog.admin.adminMember.model.dto;

import java.util.Date;

public class adminUserDTO {

    private int user_code;
    private String id;
    private String password;
    private String classify;
    private String name;
    private Date connection_date;
    private Date creation_date;
    private String withdraw_state;

    public adminUserDTO() {
    }

    public adminUserDTO(int user_code, String id, String password, String classify, String name, Date connection_date, Date creation_date, String withdraw_state) {
        this.user_code = user_code;
        this.id = id;
        this.password = password;
        this.classify = classify;
        this.name = name;
        this.connection_date = connection_date;
        this.creation_date = creation_date;
        this.withdraw_state = withdraw_state;
    }

    public int getUser_code() {
        return user_code;
    }

    public void setUser_code(int user_code) {
        this.user_code = user_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getConnection_date() {
        return connection_date;
    }

    public void setConnection_date(Date connection_date) {
        this.connection_date = connection_date;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getWithdraw_state() {
        return withdraw_state;
    }

    public void setWithdraw_state(String withdraw_state) {
        this.withdraw_state = withdraw_state;
    }

    @Override
    public String toString() {
        return "adminUserDTO{" +
                "user_code=" + user_code +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", classify='" + classify + '\'' +
                ", name='" + name + '\'' +
                ", connection_date=" + connection_date +
                ", creation_date=" + creation_date +
                ", withdraw_state='" + withdraw_state + '\'' +
                '}';
    }
}
