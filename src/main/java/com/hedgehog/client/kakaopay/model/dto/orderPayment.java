package com.hedgehog.client.kakaopay.model.dto;

import lombok.Data;

@Data
public class orderPayment {




    //주문상세내역

    //tbl_order_details
    private String orderDetailsCode; //주문상세내역코드
    private String count; // 개수
    private String productCode; //제품코드
    private String orderCode; // 주문내역코드
    private String deliveryCharge; // 배송비
    private String point; //적립포인트
    private String reducedPrice; // 할인가격
    private String costPrice; // 원가격
    private String finalPrice; // 최종가격
    private String optionCode; // 색상코드




    //tbl_payment 겳제내역
    private String paymentCode; //결제내역코드
    private String details; //결제정보
    private String completeState; //결제완료유무
    private String date; //결제시기
    private String state; //결제상태

    //배송내역 tbl_delivery

    private String deliveryCode; //배송내역코드
    private String deliveryAddress; //배송지
    private String requests; //배송요청사항
    private String arrivalTime;
    private String recipentName; //받는사람이름
    private String recipientPhone;//받는사람번호
//    private String state; //배송상태




}
