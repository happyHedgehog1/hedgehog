package com.hedgehog.client.clientOrder.model.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//카카오페이 결제 준비를 윈한 DTO
public class ReadyToPaymentDTO {
    private String cid;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private String quantity;
    private String total_amount;
    private String tax_free_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;



}
