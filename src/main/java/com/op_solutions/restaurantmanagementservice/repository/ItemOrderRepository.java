package com.op_solutions.restaurantmanagementservice.repository;

import com.op_solutions.restaurantmanagementservice.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder,Long> {

    @Query("select io from ItemOrder io where io.kitchenItemStatus = 'PENDING'")
    List<ItemOrder> getItemOrderListByKitchenPending();

    @Query("select io from ItemOrder io where io.kitchenItemStatus = 'INPROGRESS'")
    List<ItemOrder> getItemOrderListByKitchenInProgress();

    @Query("select io from ItemOrder io where io.kitchenItemStatus = 'COMPLETED'")
    List<ItemOrder> getItemOrderListByKitchenCompleted();
}
