package com.hedgehog.admin.adminMember.model.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdminEmailMessage {
    private String to;
    private String subject;
    private String message;
}
