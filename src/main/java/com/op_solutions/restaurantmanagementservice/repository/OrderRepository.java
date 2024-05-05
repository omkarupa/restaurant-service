package com.op_solutions.restaurantmanagementservice.repository;

import com.op_solutions.restaurantmanagementservice.entity.Order;
import com.op_solutions.restaurantmanagementservice.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByRestaurantTable(RestaurantTable restaurantTable);
}
