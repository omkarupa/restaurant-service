package com.op_solutions.restaurant_service.dtos;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
	
    private Long menuItemId;
    private String itemName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;


}
