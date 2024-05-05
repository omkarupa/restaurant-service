package com.op_solutions.restaurantmanagementservice.controller;

import com.op_solutions.restaurantmanagementservice.entity.MenuItem;
import com.op_solutions.restaurantmanagementservice.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuItems")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class MenuItemController {


    private final MenuItemService menuItemService;


    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long menuItemId)
    {
        return menuItemService.getMenuItemById(menuItemId);
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuItemListByCategory(@PathVariable String category)
    {
        return menuItemService.getMenuItemListByCategory(category);
    }


    @GetMapping("")
    public List<MenuItem> getMenuItemList()
    {
        return menuItemService.getMenuItemList();
    }

    @PostMapping("/menuItem")
    public ResponseEntity<MenuItem> saveMenuItem(@RequestBody MenuItem menuItem)
    {
        return null;
    }

    @PutMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuItem menuItem,@PathVariable Long menuItemId)
    {
        return null;
    }

    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> deleteMenuItem(@PathVariable Long menuItemId)
    {
        return null;
    }



}

