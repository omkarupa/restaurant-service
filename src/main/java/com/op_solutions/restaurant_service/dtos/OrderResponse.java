package com.op_solutions.restaurant_service.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.op_solutions.restaurant_service.models.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
	
	 	private Long orderId;
	    private UUID sessionId;
	    private Long tableId;
	    private OrderStatus status;
	    private List<OrderItemResponse> orderedItems;
	    private BigDecimal totalAmount;

}
