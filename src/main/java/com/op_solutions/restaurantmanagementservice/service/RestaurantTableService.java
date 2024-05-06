package com.op_solutions.restaurantmanagementservice.service;

import com.op_solutions.restaurantmanagementservice.entity.RestaurantTable;
import com.op_solutions.restaurantmanagementservice.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;


    public ResponseEntity<RestaurantTable> getRestaurantTableById(Integer tableNumber)
    {
        Optional<RestaurantTable> restaurantTable = restaurantTableRepository.findById(tableNumber);

        if(restaurantTable.isPresent())
        {
            return ResponseEntity.ok().body(restaurantTable.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public Optional<RestaurantTable> getOptionalRestaurantTableById(Integer restaurantTableId)
    {
        return restaurantTableRepository.findById(restaurantTableId);
    }


    public List<RestaurantTable> getRestaurantTableList()
    {
        return restaurantTableRepository.findAll();
    }


    public ResponseEntity<RestaurantTable> saveRestaurantTable(RestaurantTable restaurantTable)
    {
        if(restaurantTable != null)
        {
            RestaurantTable saveRestaurantTable = restaurantTableRepository.save(restaurantTable);

            return ResponseEntity.ok().body(saveRestaurantTable);
        }

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<RestaurantTable> updateRestaurantTable(RestaurantTable restaurantTable,Integer restaurantTableId)
    {

        Optional<RestaurantTable> restaurantTableDB = restaurantTableRepository.findById(restaurantTableId);

        if(restaurantTableDB.isPresent())
        {
            RestaurantTable updateRestaurantTable = restaurantTableRepository.save(restaurantTable);

            return ResponseEntity.ok().body(updateRestaurantTable);
        }
        else {
            return saveRestaurantTable(restaurantTable);
        }

    }

    public ResponseEntity<RestaurantTable> deleteRestaurantTable(Integer restaurantTableId)
    {
        Optional<RestaurantTable> restaurantTableDB = restaurantTableRepository.findById(restaurantTableId);

        if(restaurantTableDB.isPresent())
        {
            restaurantTableRepository.deleteById(restaurantTableId);

            return ResponseEntity.ok().body(restaurantTableDB.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
