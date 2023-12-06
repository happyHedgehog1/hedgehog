package com.hedgehog.admin.adminMember.model.dto;

import java.util.Date;

public class adminMemberDTO {
    private int member_code;
    private Date birthday;
    private String gender;
    private String email_consent;
    private int point;
    private int cumulative_amount;

    public adminMemberDTO() {
    }

    public adminMemberDTO(int member_code, Date birthday, String gender, String email_consent, int point, int cumulative_amount) {
        this.member_code = member_code;
        this.birthday = birthday;
        this.gender = gender;
        this.email_consent = email_consent;
        this.point = point;
        this.cumulative_amount = cumulative_amount;
    }

    public int getMember_code() {
        return member_code;
    }

    public void setMember_code(int member_code) {
        this.member_code = member_code;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail_consent() {
        return email_consent;
    }

    public void setEmail_consent(String email_consent) {
        this.email_consent = email_consent;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCumulative_amount() {
        return cumulative_amount;
    }

    public void setCumulative_amount(int cumulative_amount) {
        this.cumulative_amount = cumulative_amount;
    }

    @Override
    public String toString() {
        return "adminMemberDTO{" +
                "member_code=" + member_code +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", email_consent='" + email_consent + '\'' +
                ", point=" + point +
                ", cumulative_amount=" + cumulative_amount +
                '}';
    }
}
