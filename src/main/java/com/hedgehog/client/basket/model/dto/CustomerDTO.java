package com.hedgehog.client.basket.model.dto;

public class CustomerDTO {

    private int customerCode;
    private String memberStatus; //회원여부
    private String email;
    private String phone;
    private int certificationNumber; //인증코드번호

    public CustomerDTO() {
    }

    public CustomerDTO(int customerCode, String memberStatus, String email, String phone, int certificationNumber) {
        this.customerCode = customerCode;
        this.memberStatus = memberStatus;
        this.email = email;
        this.phone = phone;
        this.certificationNumber = certificationNumber;
    }

    public int getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(int customerCode) {
        this.customerCode = customerCode;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
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

    public int getCertificationNumber() {
        return certificationNumber;
    }

    public void setCertificationNumber(int certificationNumber) {
        this.certificationNumber = certificationNumber;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerCode=" + customerCode +
                ", memberStatus='" + memberStatus + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", certificationNumber=" + certificationNumber +
                '}';
    }
}
