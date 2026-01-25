package com.op_solutions.restaurant_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.op_solutions.restaurant_service.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	Optional<OrderItem> findByOrderIdAndMenuItemId(Long orderId, Long id);

	List<OrderItem> findByOrderId(Long orderId);

}
