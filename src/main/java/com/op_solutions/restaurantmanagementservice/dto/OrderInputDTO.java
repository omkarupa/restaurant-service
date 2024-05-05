package com.op_solutions.restaurantmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInputDTO {

    private Long orderId;
    private Long staffId;
    private Integer tableNumber;
    private List<ItemOrderInputDTO> itemOrderList;
    private BigDecimal totalPrice;

}
