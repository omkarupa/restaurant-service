package com.op_solutions.restaurantmanagementservice.controller;


import com.op_solutions.restaurantmanagementservice.dto.OrderInputDTO;
import com.op_solutions.restaurantmanagementservice.entity.Order;
import com.op_solutions.restaurantmanagementservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class OrderController {


    private final OrderService orderService;


    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId)
    {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("")
    public List<Order> getOrderList()
    {
        return orderService.getOrderList();
    }

    @PostMapping("/order")
    public ResponseEntity<Order> saveOrder(@RequestBody OrderInputDTO order)
    {
        return orderService.saveOrder(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable Long orderId)
    {
        return null;
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long orderId)
    {
        return null;
    }

    @GetMapping("/table-number/{tableNumber}")
    public ResponseEntity<Order> getOrderDetailsByTable(@PathVariable Integer tableNumber)
    {
        if(orderService.getOrderDetailsByTable(tableNumber).isPresent())
        {
            return ResponseEntity.ok().body(orderService.getOrderDetailsByTable(tableNumber).get());
        }

        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

