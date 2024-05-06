package com.op_solutions.restaurantmanagementservice.repository;

import com.op_solutions.restaurantmanagementservice.entity.Order;
import com.op_solutions.restaurantmanagementservice.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByRestaurantTable(RestaurantTable restaurantTable);

    @Query("SELECT DISTINCT o FROM Order o JOIN FETCH o.itemsOrderedList io WHERE io.kitchenItemStatus = :status" )
    List<Order> findOrdersWithKitchenItemStatus(String status);
}
