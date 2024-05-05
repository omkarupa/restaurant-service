package com.op_solutions.restaurantmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderInputDTO {

    private Long menuItemId;
    private Integer quantity;
    private BigDecimal subTotal;

}
