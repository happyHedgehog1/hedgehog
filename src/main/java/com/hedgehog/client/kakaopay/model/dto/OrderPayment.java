package com.hedgehog.client.kakaopay.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class OrderPayment {


//    @Data 애노테이션은 Lombok에서 제공하는 기능으로,
//    클래스 내의 필드에 대한 게터(Getter), 세터(Setter), toString, equals, hashCode 등의 메소드를
//    자동으로 생성해주는 기능을 합니다.
//    @Data 애노테이션을 사용하면 필드에 대한 게터와 세터를 따로 작성하지 않아도 되지만,
//    경우에 따라서는 특정 필드에 대해서는 추가적인 로직이 필요할 수 있습니다.



    //주문상세내역
    private int userCode;

    //tbl_order_details
    private int orderDetailsCode; //주문상세내역코드
    private int orderCode; // 주문내역코드
    private List<Integer> count; // 개수
    private List<Integer> productCode; //제품코드
    private int deliveryCharge; // 배송비
    private int pointCharge; //적립포인트
    private int reducedPrice; // 할인가격
    private int costPrice; // 원가격
    private int finalPrice; // 최종가격
    private String optionCode; // 색상코드
    private int reviewPoint;//리뷰포인트


    //tbl_order
    private int customerCode; //사용자코드
    private Timestamp creationDate; // 생성시기
    private int pointUsage; // 포인트 사용량
//    private String orderCode;
//    private String productCode;
//    private String state;//배송상태


    //tbl_order_list
//    orderCode;
//    deliveryCode;
//    paymentCode;

    //==========================tbl_prodcut
//    private String productCode;
    private String productName;

    //==========================tbl_product_img
    private String imgCode; //이미지코드
    private String imageClassification; // 썸네일
    private String convertPath; //이미지파일경로
//    private String productCode;




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
