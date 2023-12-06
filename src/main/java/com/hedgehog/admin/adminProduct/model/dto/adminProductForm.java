package com.hedgehog.admin.adminProduct.model.dto;

import java.util.Date;

public class adminProductForm {
    private String prdCondition;
    private String serachCondition;
    private String serachValue;
    private String prdSerchStartPrice;
    private String prdSerchEndPrice;
    private String category1;
    private String category2;
    private String proSearchStartDay;
    private String proSearchEndDay;

    public adminProductForm(String prdCondition, String serachCondition, String serachValue, String prdSerchStartPrice, String prdSerchEndPrice, String category1, String category2, String proSearchStartDay, String proSearchEndDay) {
        this.prdCondition = prdCondition;
        this.serachCondition = serachCondition;
        this.serachValue = serachValue;
        this.prdSerchStartPrice = prdSerchStartPrice;
        this.prdSerchEndPrice = prdSerchEndPrice;
        this.category1 = category1;
        this.category2 = category2;
        this.proSearchStartDay = proSearchStartDay;
        this.proSearchEndDay = proSearchEndDay;
    }

    public adminProductForm() {
    }

    public String getPrdCondition() {
        return prdCondition;
    }

    public String getSerachCondition() {
        return serachCondition;
    }

    public String getSerachValue() {
        return serachValue;
    }

    public String getPrdSerchStartPrice() {
        return prdSerchStartPrice;
    }

    public String getPrdSerchEndPrice() {
        return prdSerchEndPrice;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getProSearchStartDay() {
        return proSearchStartDay;
    }

    public String getProSearchEndDay() {
        return proSearchEndDay;
    }

    public void setPrdCondition(String prdCondition) {
        this.prdCondition = prdCondition;
    }

    public void setSerachCondition(String serachCondition) {
        this.serachCondition = serachCondition;
    }

    public void setSerachValue(String serachValue) {
        this.serachValue = serachValue;
    }

    public void setPrdSerchStartPrice(String prdSerchStartPrice) {
        this.prdSerchStartPrice = prdSerchStartPrice;
    }

    public void setPrdSerchEndPrice(String prdSerchEndPrice) {
        this.prdSerchEndPrice = prdSerchEndPrice;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public void setProSearchStartDay(String proSearchStartDay) {
        this.proSearchStartDay = proSearchStartDay;
    }

    public void setProSearchEndDay(String proSearchEndDay) {
        this.proSearchEndDay = proSearchEndDay;
    }

    @Override
    public String toString() {
        return "adminProductForm{" +
                "prdCondition='" + prdCondition + '\'' +
                ", serachCondition='" + serachCondition + '\'' +
                ", serachValue='" + serachValue + '\'' +
                ", prdSerchStartPrice='" + prdSerchStartPrice + '\'' +
                ", prdSerchEndPrice='" + prdSerchEndPrice + '\'' +
                ", category1='" + category1 + '\'' +
                ", category2='" + category2 + '\'' +
                ", proSearchStartDay='" + proSearchStartDay + '\'' +
                ", proSearchEndDay='" + proSearchEndDay + '\'' +
                '}';
    }
}
