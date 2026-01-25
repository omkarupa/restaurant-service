package com.op_solutions.restaurant_service.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.op_solutions.restaurant_service.dtos.ItemRequest;
import com.op_solutions.restaurant_service.dtos.MenuItemResponse;
import com.op_solutions.restaurant_service.dtos.OrderItemResponse;
import com.op_solutions.restaurant_service.dtos.OrderResponse;
import com.op_solutions.restaurant_service.exception.BusinessException;
import com.op_solutions.restaurant_service.exception.InvalidOperationException;
import com.op_solutions.restaurant_service.exception.ResourceNotFoundException;
import com.op_solutions.restaurant_service.models.Bill;
import com.op_solutions.restaurant_service.models.BillStatus;
import com.op_solutions.restaurant_service.models.GuestSession;
import com.op_solutions.restaurant_service.models.Order;
import com.op_solutions.restaurant_service.models.OrderItem;
import com.op_solutions.restaurant_service.models.OrderStatus;
import com.op_solutions.restaurant_service.repository.BillRepository;
import com.op_solutions.restaurant_service.repository.OrderItemRepository;
import com.op_solutions.restaurant_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuService menuService;
    private final BillingService billingService;
    private final SessionService sessionService;

	
	public OrderResponse addMenuItem(ItemRequest request)
	{
		return null;
	}
	
	public OrderResponse addItems(UUID sessionId,List<ItemRequest> items) throws InvalidOperationException
	{
		GuestSession session = sessionService.getActiveSession(sessionId);
		
		Order order = orderRepository.findBySessionIdAndStatus(sessionId, OrderStatus.OPEN).orElse(null);
		
		if(order == null)
		{
			order = createNewOrder(sessionId);
		}
		
		
		
		for(ItemRequest itemRequest : items)
		{
			addOrUpdateOrderItem(order,itemRequest);
		}
		
		Bill bill = recalculateBill(session);

		
		OrderResponse response = OrderResponse.builder()
				.orderedItems(getOrderItemList(order))
				.sessionId(sessionId)
				.status(order.getStatus())
				.tableId(session.getTableId())
				.orderId(order.getOrderId())
				.totalAmount(bill.getTotalAmount())
				.build();
		
		return response;
	}
	
	public List<OrderItemResponse> getOrderItemList(Order order)
	{
		List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getOrderId());
		return orderItemList.stream().map(item->{
			MenuItemResponse menuItem = menuService.getMenuItem(item.getMenuItemId());

			return OrderItemResponse.builder()
			.menuItemId(item.getMenuItemId())
			.itemName(menuItem.getName())
			.price(menuItem.getPrice())
			.quantity(item.getQuantity())
			.total(menuItem.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
			.build();
		}).toList();

	}
	
	public OrderItemResponse toOrderedItems(ItemRequest itemRequest)
	{
		MenuItemResponse menuItem = menuService.getMenuItem(itemRequest.getMenuItemId());
		
		return OrderItemResponse.builder()
				.menuItemId(itemRequest.getMenuItemId())
				.itemName(menuItem.getName())
				.price(menuItem.getPrice())
				.quantity(itemRequest.getQuantity())
				.total(menuItem.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())))
				.build();
	}
	
	
	
	private Bill recalculateBill(GuestSession session) {

		Bill bill = billingService.getBillBySessionId(session.getSessionId());
		
		if(bill == null)
		{
			bill = new Bill();
			bill.setSessionId(session.getSessionId());
			bill.setTableId(session.getTableId());
			bill.setCreatedAt(LocalDateTime.now());
			bill.setStatus(BillStatus.OPEN);
			
		}
		
		List<OrderItem> orderItemsList = orderItemRepository.findByOrderId(orderRepository.findBySessionIdAndStatus(session.getSessionId(), OrderStatus.OPEN).map(Order:: getOrderId).orElseThrow(() -> new BusinessException("Order not found")));
		
		BigDecimal subTotal = orderItemsList.stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal:: add);
		
		
		bill.setSubTotal(subTotal);
		
		bill.setTaxAmount(subTotal.multiply(new BigDecimal(0.0)));
		
		bill.setTotalAmount(bill.getSubTotal().add(bill.getTaxAmount()));
		
		return billingService.saveBill(bill);
		
	}

	private void addOrUpdateOrderItem(Order order, ItemRequest itemRequest) {
		
		MenuItemResponse menuItem = menuService.getMenuItem(itemRequest.getMenuItemId());
		
		if(!menuItem.isAvailable())
		{
			throw new ResourceNotFoundException("Item is not available");
		}
		
		
		OrderItem orderItem = orderItemRepository.findByOrderIdAndMenuItemId(order.getOrderId(),menuItem.getId()).orElse(null);
		
		if(orderItem == null)
		{
			orderItem = OrderItem.builder()
					.orderId(order.getOrderId())
					.menuItemId(menuItem.getId())
					.price(menuItem.getPrice())
					.quantity(itemRequest.getQuantity())
					.build();
		}
		else {
			orderItem.setQuantity(orderItem.getQuantity() + itemRequest.getQuantity());
		}
		
		orderItemRepository.save(orderItem);
		
		
	}

	private Order createNewOrder(UUID sessionId) {
		
		Order order = Order.builder()
				.sessionId(sessionId)
				.createdAt(LocalDateTime.now())
				.status(OrderStatus.OPEN)
				.build();	
		
		return orderRepository.save(order);
	}

	public OrderResponse getOrderBySession(UUID sessionId)
	{
		return null;
	}
	
	
	public void updateOrderStatus(UUID orderId,OrderStatus status)
	{
		
	}
	
	
	
}
