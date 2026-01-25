package com.op_solutions.restaurant_service.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class OrderRequest {
	
	UUID sessionId;
	List<ItemRequest> itemRequests;

}
