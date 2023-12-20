package com.hedgehog.client.kakaopay.model.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString


public class Amount {//결제금액정보
    private int total;
    private int tax_free;
    private int vat;
    private int point;
    private int discount;
}
