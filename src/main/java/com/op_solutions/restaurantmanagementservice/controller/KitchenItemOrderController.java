package com.op_solutions.restaurantmanagementservice.controller;

import com.op_solutions.restaurantmanagementservice.entity.ItemOrder;
import com.op_solutions.restaurantmanagementservice.service.ItemOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("kitchen-preparation")
public class KitchenItemOrderController {

    private final ItemOrderService itemOrderService;

    @GetMapping("/pending")
    public List<ItemOrder> getItemOrderByKitchenStatusAsPending()
    {
        return itemOrderService.getItemOrderByKitchenStatusPending();
    }

    @GetMapping("/in-progress")
    public List<ItemOrder> getItemOrderByKitchenStatusAsInProgress()
    {
        return itemOrderService.getItemOrderListByKitchenInProgress();
    }

    @GetMapping("/completed")
    public List<ItemOrder> getItemOrderByKitchenStatusAsCompleted()
    {
        return itemOrderService.getItemOrderByKitchenStatusCompleted();
    }


}
