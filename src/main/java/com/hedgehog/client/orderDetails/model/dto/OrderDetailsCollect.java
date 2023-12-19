package com.hedgehog.client.orderDetails.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailsCollect {
    // tbl_order
    private int orderCode; // 주문 코드
    private int orderCustomerCode; // 유저 코드
    private Timestamp orderCreationDate; // 주문 작성 시기
    private int pointUsage; // 포인트 사용량
    private String orderState; // 주문 상태

    // 아래 tbl_delivery 와 tbl_payment는 tbl_order_list 와 이어진다.
    // 여기서 order_code를 이용하여 delivery_code 와 payment_code를 가져온다.
    // tbl_delievery
    private int deliveryCode; // 배송 코드
    private String deliveryAddress; // 주소. 상황에 따라 출력 방식이 달라질 수 있음.
    private String deliveryRequest;
    private Timestamp arrivalTime;
    private String recipientName; // 받는 사람 이름
    private String recipientPhone; // 받는 사람 휴대전화
    private String deliveryState; // 배송상태 -> 배송중, 배송완료, 배송취소

    // tbl_payment
    private int paymentCode; // 결제 코드
    private String paymentDetails; // 결제 정보(카카오페이인지 신한카드인지.. 등등)
    private String paymentCompleteState; // 결제 완료 유무.
    private Timestamp paymentCompleteDate; // 결제 완료 시기
    private String paymentState; // 결제상태 -> 환불, 교환, 결제완료, 결제전


    // 여기는 null이어도 된다.
    // tbl_exchange와 tbl_refund.
    // tbl_payment 의 payment_code와 이어진다.
    private int exchangeCode; // 교환 코드
    private Timestamp exchangeDate; // 교환 시기
    private String exchangeCause; // 교환 이유
    private Integer exchangeInquiryCode; // 교환의 이유가 된 문의글 코드(nullable)

    private int refundCode; // 환불 코드
    private Timestamp refundDate; // 환불 시기
    private String refundCause; // 환불 이유
    private Integer refundInquiryCode; // 환불의 이유가 된 문의글 코드(nullable)


    // tbl_order_details -> tbl_order 의 order_code를 이용해서 리스트로 가져옴
    private List<OrderDetailsDTO> orderDetailsList;
}
