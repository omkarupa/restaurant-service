package com.op_solutions.restaurantmanagementservice.service;

import com.op_solutions.restaurantmanagementservice.entity.MenuItem;
import com.op_solutions.restaurantmanagementservice.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;



    public ResponseEntity<MenuItem> getMenuItemById(Long menuItemId)
    {
        Optional<MenuItem> menuItem = menuItemRepository.findById(menuItemId);

        if(menuItem.isPresent())
        {
            return ResponseEntity.ok().body(menuItem.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Optional<MenuItem> getOptionalMenuItemById(Long menuItemId)
    {
        return menuItemRepository.findById(menuItemId);
    }


    public List<MenuItem> getMenuItemList()
    {
        return menuItemRepository.findAll();
    }


    public ResponseEntity<MenuItem> saveMenuItem(MenuItem menuItem)
    {
        MenuItem saveMenuItem = menuItemRepository.save(menuItem);

        return ResponseEntity.ok().body(saveMenuItem);

    }

    public ResponseEntity<MenuItem> updateMenuItem(MenuItem menuItem,Long menuItemId)
    {

        Optional<MenuItem> menuItemDB = menuItemRepository.findById(menuItemId);

        if(menuItemDB.isPresent())
        {
            MenuItem updateMenuItem = menuItemRepository.save(menuItem);

            return ResponseEntity.ok().body(updateMenuItem);
        }
        else {
            return saveMenuItem(menuItem);
        }

    }

    public ResponseEntity<MenuItem> deleteMenuItem(Long menuItemId)
    {
        Optional<MenuItem> menuItemDB = menuItemRepository.findById(menuItemId);

        if(menuItemDB.isPresent())
        {
            menuItemRepository.deleteById(menuItemId);

            return ResponseEntity.ok().body(menuItemDB.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public List<MenuItem> getMenuItemListByCategory(String category)
    {
        return menuItemRepository.findByCategory(category);
    }


}
