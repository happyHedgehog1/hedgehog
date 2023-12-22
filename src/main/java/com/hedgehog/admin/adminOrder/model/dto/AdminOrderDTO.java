package com.hedgehog.admin.adminOrder.model.dto;

import com.hedgehog.admin.adminMember.model.dto.AdminAllMemberDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminCustomerDTO;
import com.hedgehog.admin.adminMember.model.dto.AdminUserDTO;
import com.hedgehog.admin.adminProduct.model.dto.AdminProductDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminOrderDTO extends AdminAllMemberDTO {
    private List<AdminOrderDetailDTO> orderDetail;
    private int orderCode;
    private int customerCode;
    private String creationDate;
    private int sumPrice;
    private int pointUsage;
    private String state;
    private int productCode;
    private AdminUserDTO userDTO;
    private AdminProductDTO productDTO;
    private int totalCount;
    private AdminCustomerDTO adminCustomerDTO;
    private AdminDeliveryDTO deliveryDTO;
    private String cause;

}
