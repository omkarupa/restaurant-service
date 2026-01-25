package com.op_solutions.restaurant_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op_solutions.restaurant_service.dtos.ItemRequest;
import com.op_solutions.restaurant_service.dtos.OrderRequest;
import com.op_solutions.restaurant_service.dtos.OrderResponse;
import com.op_solutions.restaurant_service.exception.InvalidOperationException;
import com.op_solutions.restaurant_service.models.OrderStatus;
import com.op_solutions.restaurant_service.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
		
	@PostMapping("/add-items")
	public OrderResponse addItem(@RequestBody OrderRequest orderRequest)  throws InvalidOperationException
	{
		return orderService.addItems(orderRequest.getSessionId(),orderRequest.getItemRequests());
	}
	
	
	
	@GetMapping("/session/{sessionId}")
	public OrderResponse getOrder(UUID sessionId)
	{
		return orderService.getOrderBySession(sessionId);
	}
	
	@PutMapping("{orderId}/status")
	public void updateStatus(@PathVariable UUID orderId,@RequestParam OrderStatus status)
	{
		orderService.updateOrderStatus(orderId, status);
	}
	
	
	

}
