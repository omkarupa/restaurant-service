package com.op_solutions.restaurantmanagementservice.controller;

import com.op_solutions.restaurantmanagementservice.entity.RestaurantTable;
import com.op_solutions.restaurantmanagementservice.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
@CrossOrigin("http://localhost:4200")
public class TableController {

    private final RestaurantTableService restaurantTableService;

    @GetMapping("")
    public List<RestaurantTable> getRestaurantTableList()
    {
        return  restaurantTableService.getRestaurantTableList();
    }

    @GetMapping("/{tableNumber}")
    public ResponseEntity<RestaurantTable> getRestaurantTableById(@PathVariable Integer tableNumber)
    {
        return  restaurantTableService.getRestaurantTableById(tableNumber);
    }

}
