package com.hedgehog.client.basket.model.dto;

public class OptionListDTO {

    private int productCode;
    private String optionCode;
    private int stock;
    private String exposureStatus;
    private int salesVolume; //판매량


    public OptionListDTO() {
    }

    public OptionListDTO(int productCode, String optionCode, int stock, String exposureStatus, int salesVolume) {
        this.productCode = productCode;
        this.optionCode = optionCode;
        this.stock = stock;
        this.exposureStatus = exposureStatus;
        this.salesVolume = salesVolume;
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

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    @Override
    public String toString() {
        return "OptionListDTO{" +
                "productCode=" + productCode +
                ", optionCode='" + optionCode + '\'' +
                ", stock=" + stock +
                ", exposureStatus='" + exposureStatus + '\'' +
                ", salesVolume=" + salesVolume +
                '}';
    }
}
