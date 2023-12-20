package com.hedgehog.client.auth.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistMailDTO {
    private String content;
    private String title;
}
