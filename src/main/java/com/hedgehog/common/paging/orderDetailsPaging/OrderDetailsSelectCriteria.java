package com.hedgehog.common.paging.orderDetailsPaging;


import com.hedgehog.client.orderDetails.model.dto.OrderDTO;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailsSelectCriteria {
    private int pageNo; // 몇 페이지를 요청하는지.
    private int totalCount; // 전체 게시물 수. 말그대로 마지막 페이지가 무엇인지.
    private int limit; // 한 페이지에 몇개의 게시물을 보여줄지.
    private int buttonAmount; // 한번에 몇개의 버튼을 보여줄 지.
    private int maxPage; // 가장 마지막 페이지
    /*페이지가 있을때 a번 페이지버튼부터 b번 페이지 버튼이 나올 것이다. 이때 아래를 사용한다.*/
    private int startPage; // 한번에 보여줄 페이징 버튼의 시작하는 페이지 수
    private int endPage; // 한번에 보여줄 페이징 버튼의 마지막 페이지 수
    private int startRow; // DB 조회 시 최신 글 부터 조회해야 하는 행의 시작 수 -> 어떤 페이지에서 존재하는 데이터의 제일 상단값
    private int endRow; // DB 조회 시 최신글부터 조회해야 하는 행의 마지막 수 -> 어떤 페이지에서 존재하는 데이터의 제일 하단값
    private OrderDTO order;
}
