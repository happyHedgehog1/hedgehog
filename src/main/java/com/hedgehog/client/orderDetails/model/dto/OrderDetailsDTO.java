package com.hedgehog.client.orderDetails.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailsDTO {
    // tbl_order_details + tbl_product + tbl_product_img
    private int orderDetailsCode; // 상세 주문 코드
    private int count; // 제품 구매 갯수
    private int productCode; // 제품 코드
    private String productName; // tbl_product 의 product_name
    private String productImage; // tbl_product_img 의 convert_path
    private String optionCode; // 옵션 코드
    private String optionName; // 옵션 이름 -> option_code로 가져올 예정 -> tbl_option 이용
    private int deliveryCharge; // 배달료
    private int pointCharge; // 포인트 충전량
    private int reducedPrice; // 할인 가격
    private int costPrice; // 제품가격
    private int finalPrice; // 최종가격 >> 제품가격 + 배달료 - 할인가격
    private int reviewPoint; // 리뷰포인트
}
