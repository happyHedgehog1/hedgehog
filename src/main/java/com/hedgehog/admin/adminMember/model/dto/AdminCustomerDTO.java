package com.hedgehog.admin.adminMember.model.dto;

public class AdminCustomerDTO {

    private int customer_code;
    private String member_state;
    private String email;
    private String phone;
    private String certification_number;

    public AdminCustomerDTO() {
    }

    public int getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(int customer_code) {
        this.customer_code = customer_code;
    }

    public String getMember_state() {
        return member_state;
    }

    public void setMember_state(String member_state) {
        this.member_state = member_state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCertification_number() {
        return certification_number;
    }

    public void setCertification_number(String certification_number) {
        this.certification_number = certification_number;
    }

    public AdminCustomerDTO(int customer_code, String member_state, String email, String phone, String certification_number) {
        this.customer_code = customer_code;
        this.member_state = member_state;
        this.email = email;
        this.phone = phone;
        this.certification_number = certification_number;


    }

    @Override
    public String toString() {
        return "adminCustomerDTO{" +
                "customer_code=" + customer_code +
                ", member_state='" + member_state + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", certification_number='" + certification_number + '\'' +
                '}';
    }
}
