package com.hedgehog.admin.adminMember.model.dto;


import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDTO;
import com.hedgehog.admin.adminOrder.model.dto.AdminOrderDetailDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminAllMemberDTO {
    private int member_code;
    private Date birthday;
    private String gender;
    private String email_consent;
    private int point;
    private int cumulative_amount;
    private AdminCustomerDTO customer;
    private AdminUserDTO user;
    private AdminOrderDTO orderDTO;
    private int totalPoint;
    private List<AdminOrderDetailDTO> orderDetail;
}
