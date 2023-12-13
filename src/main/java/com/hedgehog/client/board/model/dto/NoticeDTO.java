package com.hedgehog.client.board.model.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor@AllArgsConstructor@Getter@Setter@ToString
public class NoticeDTO {
    private int postCode;
    private Timestamp writeDate;
    private String content;
    private String title;
    private int views;
    private String postType;
    private int userCode;
    private String state;
    private Timestamp modifyDate; // 여기까지 tbl_admin_bulletin_board

    private String userId; // 여기는 tbl_user
}
