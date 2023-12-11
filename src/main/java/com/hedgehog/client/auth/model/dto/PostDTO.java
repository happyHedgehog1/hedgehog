package com.hedgehog.client.auth.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostDTO {
    private String postType;
    private String content;
}
