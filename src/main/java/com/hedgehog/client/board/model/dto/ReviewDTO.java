package com.hedgehog.client.board.model.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
    private int reviewCode; // tbl_review의 review_code 리뷰번호
    private int productCode; // tbl_review의 product_code 제품번호
    private String productName; // tbl_product의 product_name
    private String productImgPath; // tbl_product_img의 convert_path. 썸네일 이미지의 주소. image_classification이 Thumbnails인 경우만.
    private String optionCode; // tbl_review의 option_code 옵션코드(#000000)
    private String optionName; // tbl_option의 name 옵션이름(흰색)
    private int grade; // tbl_review의 grade 별점
    private String content; // tbl_review의 content 내부내용
    private Timestamp writeDate; //tbl_review의 write_date 작성시간. -> 수정 불가능하게? -> 가능은 한데 굳이 의미가 있는가.
    private int memberCode; // tbl_review의 member_code 멤버코드
    private String memberId; // tbl_user의 id 유저아이디
}
