package com.op_solutions.restaurantmanagementservice.controller;

import com.op_solutions.restaurantmanagementservice.dto.OrderKitchenDTO;
import com.op_solutions.restaurantmanagementservice.entity.ItemOrder;
import com.op_solutions.restaurantmanagementservice.entity.Order;
import com.op_solutions.restaurantmanagementservice.mapper.MapperUtil;
import com.op_solutions.restaurantmanagementservice.service.ItemOrderService;
import com.op_solutions.restaurantmanagementservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("kitchen-preparation")
public class KitchenItemOrderController {

    private final ItemOrderService itemOrderService;

    private final OrderService orderService;

    private final MapperUtil mapperUtil;

    @GetMapping("")
    public List<OrderKitchenDTO> getKitchenOrderedList()
    {
        return convertOrderListToOrderKitchenDTOList(orderService.getOrderList());
    }


    @GetMapping("/pending")
    public List<OrderKitchenDTO> getItemOrderByKitchenStatusAsPending()
    {
        //return itemOrderService.getItemOrderByKitchenStatusPending();

        List<Order> orderList =  orderService.getOrdersWithKitchenItemStatus("pending");

        orderList.forEach(item -> System.out.println(item.toString()));

        return convertOrderListToOrderKitchenDTOList(orderList);

    }

    @GetMapping("/in-progress")
    public List<OrderKitchenDTO> getItemOrderByKitchenStatusAsInProgress()
    {
        List<Order> orderList =orderService.getOrdersWithKitchenItemStatus("in-progress");

        //orderList.forEach(item -> System.out.println(item));

        return convertOrderListToOrderKitchenDTOList(orderList);
    }

    @GetMapping("/completed")
    public List<OrderKitchenDTO> getItemOrderByKitchenStatusAsCompleted()
    {
        List<Order> orderList = orderService.getOrdersWithKitchenItemStatus("completed");

        return convertOrderListToOrderKitchenDTOList(orderList);

    }

    @PutMapping("/item-order/{itemOrderId}")
    public ResponseEntity<ItemOrder> updateItemOrderKitchenStatus(@PathVariable Long itemOrderId)
    {
        return itemOrderService.updateItemOrderKitchenStatus(itemOrderId);
    }

    private List<OrderKitchenDTO> convertOrderListToOrderKitchenDTOList(List<Order> orderList)
    {
        List<OrderKitchenDTO> orderKitchenDTOList = orderList.stream().map(order -> mapperUtil.mapOrderToOrderKitchenDTO(order)).collect(Collectors.toList());

        return orderKitchenDTOList;
    }


}
