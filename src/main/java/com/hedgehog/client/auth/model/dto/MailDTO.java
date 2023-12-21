package com.hedgehog.client.auth.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MailDTO {
    private String content;
    private String title;
}
