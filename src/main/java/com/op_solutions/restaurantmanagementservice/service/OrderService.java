package com.op_solutions.restaurantmanagementservice.service;


import com.op_solutions.restaurantmanagementservice.dto.OrderInputDTO;
import com.op_solutions.restaurantmanagementservice.entity.Order;
import com.op_solutions.restaurantmanagementservice.entity.RestaurantTable;
import com.op_solutions.restaurantmanagementservice.mapper.MapperUtil;
import com.op_solutions.restaurantmanagementservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;

    private final RestaurantTableService restaurantTableService;

    public ResponseEntity<Order> getOrderById(Long orderId)
    {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isPresent())
        {
            return ResponseEntity.ok().body(order.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Optional<Order> getOptionalOrderById(Long orderId)
    {
        return orderRepository.findById(orderId);
    }


    public List<Order> getOrderList()
    {
        return orderRepository.findAll();
    }


    public ResponseEntity<Order> saveOrder(OrderInputDTO orderInputDTO)
    {

        if(orderInputDTO != null)
        {
            Order mapperOrder = mapperUtil.mapOrderFromInputOrderDTO(orderInputDTO);

            System.out.println(mapperOrder.toString());

            Order saveOrder = orderRepository.save(mapperOrder);

            return ResponseEntity.ok().body(saveOrder);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<Order> updateOrder(Order order,Long orderId)
    {

        Optional<Order> orderDB = orderRepository.findById(orderId);

        if(orderDB.isPresent())
        {
            Order updateOrder = orderRepository.save(order);

            return ResponseEntity.ok().body(updateOrder);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Order> deleteOrder(Long orderId)
    {
        Optional<Order> orderDB = orderRepository.findById(orderId);

        if(orderDB.isPresent())
        {
            orderRepository.deleteById(orderId);

            return ResponseEntity.ok().body(orderDB.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Optional<Order> getOrderDetailsByTable(Integer tableNumber)
    {
        if(tableNumber != 0)
        {
            Optional<RestaurantTable> restaurantTable = restaurantTableService.getOptionalRestaurantTableById(tableNumber);

            if(restaurantTable.isPresent())
            {
                Optional<Order> order = orderRepository.findByRestaurantTable(restaurantTable.get());

                return  order;
            }
        }

        return  null;
    }

    public List<Order> getOrdersWithKitchenItemStatus(String kitchenItemStatus){
        String status = "";
        if(kitchenItemStatus.equals("pending"))
        {
            status = "PENDING";
        }
        else if (kitchenItemStatus.equals("in-progress"))
        {
            status = "INPROGRESS";
        }
        else if (kitchenItemStatus.equals("completed"))
        {
            status = "COMPLETED";
        }

        return orderRepository.findOrdersWithKitchenItemStatus(status);
    }
}
