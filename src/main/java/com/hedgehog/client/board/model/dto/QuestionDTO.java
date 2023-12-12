package com.hedgehog.client.board.model.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter@ToString
public class QuestionDTO {
    private int inquiryCode;
    private String secretState;
    private String answerState;
    private Timestamp writeDate;
    private String postType;
    private String title;
    private String content;
    private String state;
    private Timestamp modifyDate;
    private int memberCode;
    private String memberId;
}
