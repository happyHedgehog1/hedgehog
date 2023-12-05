package com.hedgehog.admin.adminProduct.model.dto;

import lombok.*;

import java.util.Date;

public class adminProductDTO {
private int productCode;
private int categoryCode;
private String productName;
private char orderableStatus;
private int price;
private Date registrationDate;
private Date modificationDate;
private char eventProgressionStatus;
private int deliveryCharge;
private Date salesStart;
private Date salesEnd;
private int reviews;
private int grade;
private optionListDTO option;

    public adminProductDTO() {
    }

    public adminProductDTO(int productCode, int categoryCode, String productName, char orderableStatus, int price, Date registrationDate, Date modificationDate, char eventProgressionStatus, int deliveryCharge, Date salesStart, Date salesEnd, int reviews, int grade, optionListDTO option) {
        this.productCode = productCode;
        this.categoryCode = categoryCode;
        this.productName = productName;
        this.orderableStatus = orderableStatus;
        this.price = price;
        this.registrationDate = registrationDate;
        this.modificationDate = modificationDate;
        this.eventProgressionStatus = eventProgressionStatus;
        this.deliveryCharge = deliveryCharge;
        this.salesStart = salesStart;
        this.salesEnd = salesEnd;
        this.reviews = reviews;
        this.grade = grade;
        this.option = option;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getProductName() {
        return productName;
    }

    public char getOrderableStatus() {
        return orderableStatus;
    }

    public int getPrice() {
        return price;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public char getEventProgressionStatus() {
        return eventProgressionStatus;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public Date getSalesStart() {
        return salesStart;
    }

    public Date getSalesEnd() {
        return salesEnd;
    }

    public int getReviews() {
        return reviews;
    }

    public int getGrade() {
        return grade;
    }

    public optionListDTO getOption() {
        return option;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrderableStatus(char orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setEventProgressionStatus(char eventProgressionStatus) {
        this.eventProgressionStatus = eventProgressionStatus;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public void setSalesStart(Date salesStart) {
        this.salesStart = salesStart;
    }

    public void setSalesEnd(Date salesEnd) {
        this.salesEnd = salesEnd;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setOption(optionListDTO option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "adminProductDTO{" +
                "productCode=" + productCode +
                ", categoryCode=" + categoryCode +
                ", productName='" + productName + '\'' +
                ", orderableStatus=" + orderableStatus +
                ", price=" + price +
                ", registrationDate=" + registrationDate +
                ", modificationDate=" + modificationDate +
                ", eventProgressionStatus=" + eventProgressionStatus +
                ", deliveryCharge=" + deliveryCharge +
                ", salesStart=" + salesStart +
                ", salesEnd=" + salesEnd +
                ", reviews=" + reviews +
                ", grade=" + grade +
                ", option=" + option +
                '}';
    }
}
