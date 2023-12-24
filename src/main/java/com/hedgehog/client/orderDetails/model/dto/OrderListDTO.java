package com.hedgehog.client.orderDetails.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@ToString
public class  OrderListDTO {
    private int orderCode; // tbl_order 의 order_code
    private Timestamp creationDate; // tbl_order 의 creation_date
    private String convertPath; // tbl_product_img 의 convert_path
    private int productCode; // tbl_order 의 product_code
    private String productName; // tbl_product 의 product_name
    private int productCount; // tbl_order_details의 count의 합. ***** 외 3개 이런식으로 표현하기 위함
    private int costPrice; // tbl_order_details의 cost_price의 합 (각 제품의 순수 판매가격의 합)
    private int deliveryCharge; // tbl_order_details의 delivery_charge의 합 (각 제품의 배송비 합)
    private int reducedPrice; // tbl_order_details의 할인가격의 합 (각 제품의 할인가격의 합) => 이벤트 같은거로
    private int pointUsage; // tbl_order 에서 쓴 포인트 량
    private int pointCharge; // tbl_order_details 에서 쌓인 포인트가격의 합(적립 포인트의 합)
    private String state; // 현재 orderList의 상태. tbl_order에 나온다.
}
