package com.hedgehog.client.board.model.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostImageDTO {
    private Integer postImageCode;
    private Integer eventCode;
    private Integer reviewCode;
    private Integer inquiryCode;
    private Timestamp createDate;
    private String convertPath;
    private String state;
    private String postType;
}
