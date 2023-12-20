package com.hedgehog.client.kakaopay.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ReadyResponse {//카카오페이에서 결제 처리 후 응답을 담기 위한 것

    private String tid; //고유 결제 번호
    private String next_redirect_pc_url; //결제 요청 완료된 후 사용자를 리다이렉션 할 url
    private String partner_order_id;//파트너사에서 전달하는 주문 고유 Id


}
