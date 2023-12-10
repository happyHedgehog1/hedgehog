package com.hedgehog.admin.adminEvent.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminEventForm {
    private String searchStartDay;
    private String searchEndDay;
    private String eventName;
    private String status;

}
