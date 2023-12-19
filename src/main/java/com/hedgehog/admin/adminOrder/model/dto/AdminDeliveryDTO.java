package com.hedgehog.admin.adminOrder.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminDeliveryDTO {
private String  deliveryCode;
private String  deliveryAddress;
private String  deliveryRequests;
private String  arrivalTime;
private String  recipientName;
private String  recipientPhone;
private String  state;
}
