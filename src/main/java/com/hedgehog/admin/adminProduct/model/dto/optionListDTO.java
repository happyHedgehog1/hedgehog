package com.hedgehog.admin.adminProduct.model.dto;

public class optionListDTO {
    private int productCode;
    private String optionCode;
    private int stock;
    private String exposureStatus;
    private int sales;

    public optionListDTO() {
    }

    public optionListDTO(int productCode, String optionCode, int stock, String exposureStatus, int sales) {
        this.productCode = productCode;
        this.optionCode = optionCode;
        this.stock = stock;
        this.exposureStatus = exposureStatus;
        this.sales = sales;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getExposureStatus() {
        return exposureStatus;
    }

    public void setExposureStatus(String exposureStatus) {
        this.exposureStatus = exposureStatus;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "optionListDTO{" +
                "productCode=" + productCode +
                ", optionCode='" + optionCode + '\'' +
                ", stock=" + stock +
                ", exposureStatus='" + exposureStatus + '\'' +
                ", sales=" + sales +
                '}';
    }
}
