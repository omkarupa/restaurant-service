package com.op_solutions.restaurantmanagementservice.service;

import com.op_solutions.restaurantmanagementservice.entity.ItemOrder;
import com.op_solutions.restaurantmanagementservice.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemOrderService {

    private final ItemOrderRepository itemOrderRepository;

    public List<ItemOrder> getItemOrderByKitchenStatusPending()
    {
        return itemOrderRepository.getItemOrderListByKitchenPending();
    }

    public List<ItemOrder> getItemOrderListByKitchenInProgress()
    {
        return itemOrderRepository.getItemOrderListByKitchenInProgress();
    }

    public List<ItemOrder> getItemOrderByKitchenStatusCompleted()
    {
        return itemOrderRepository.getItemOrderListByKitchenCompleted();
    }


}
