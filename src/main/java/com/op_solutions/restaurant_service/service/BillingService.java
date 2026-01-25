package com.op_solutions.restaurant_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.op_solutions.restaurant_service.dtos.BillResponse;
import com.op_solutions.restaurant_service.dtos.PaymentRequest;
import com.op_solutions.restaurant_service.exception.BusinessException;
import com.op_solutions.restaurant_service.models.Bill;
import com.op_solutions.restaurant_service.models.BillStatus;
import com.op_solutions.restaurant_service.models.Order;
import com.op_solutions.restaurant_service.models.OrderStatus;
import com.op_solutions.restaurant_service.repository.BillRepository;
import com.op_solutions.restaurant_service.repository.OrderRepository;
import com.op_solutions.restaurant_service.repository.SessionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillingService {
	
	private final BillRepository billRepository;
	private final OrderRepository orderRepository;
	private final SessionRepository sessionRepository;

	public BillResponse generateBill(UUID sessionId) {
		return null;
	}
	
	public Bill saveBill(Bill bill)
	{
		return billRepository.save(bill);
	}
	
	public Bill getBillBySessionId(UUID sessionId)
	{
		return billRepository.findBySessionId(sessionId).orElse(null);
	}

	public void pay(PaymentRequest request) {
		
		Bill bill = getBillBySessionId(request.getSessionId());
		
		bill.setStatus(BillStatus.PAID);
		
		Order order = orderRepository.findBySessionId(request.getSessionId()).orElseThrow(() -> new BusinessException("Order Not found for Session"));
		
		order.setStatus(OrderStatus.BILLED);
		
		orderRepository.save(order);
		
		billRepository.save(bill);
		
		
		
		
		
		
	}

}
