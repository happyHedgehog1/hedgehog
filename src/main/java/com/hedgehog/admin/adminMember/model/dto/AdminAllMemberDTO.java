package com.hedgehog.admin.adminMember.model.dto;


import java.util.Date;

public class AdminAllMemberDTO {
    private int member_code;
    private Date birthday;
    private String gender;
    private String email_consent;
    private int point;
    private int cumulative_amount;
    private AdminCustomerDTO customer;
    private AdminUserDTO user;

    public AdminAllMemberDTO() {
    }

    public AdminAllMemberDTO(int member_code, Date birthday, String gender, String email_consent, int point, int cumulative_amount, AdminCustomerDTO customer, AdminUserDTO user) {
        this.member_code = member_code;
        this.birthday = birthday;
        this.gender = gender;
        this.email_consent = email_consent;
        this.point = point;
        this.cumulative_amount = cumulative_amount;
        this.customer = customer;
        this.user = user;
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

    public AdminCustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(AdminCustomerDTO customer) {
        this.customer = customer;
    }

    public AdminUserDTO getUser() {
        return user;
    }

    public void setUser(AdminUserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "adminAllMemberDTO{" +
                "member_code=" + member_code +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", email_consent='" + email_consent + '\'' +
                ", point=" + point +
                ", cumulative_amount=" + cumulative_amount +
                ", customer=" + customer +
                ", user=" + user +
                '}';
    }
}
