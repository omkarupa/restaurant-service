package com.op_solutions.restaurant_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.op_solutions.restaurant_service.models.Order;
import com.op_solutions.restaurant_service.models.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findBySessionId(UUID sessionId);

	Optional<Order> findBySessionIdAndStatus(UUID sessionId,OrderStatus status);

	

}
