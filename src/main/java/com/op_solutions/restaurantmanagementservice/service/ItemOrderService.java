package com.op_solutions.restaurantmanagementservice.service;

import com.op_solutions.restaurantmanagementservice.entity.ItemOrder;
import com.op_solutions.restaurantmanagementservice.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public ResponseEntity<ItemOrder> updateItemOrderKitchenStatus(Long itemOrderId) {

        Optional<ItemOrder> itemOrder = itemOrderRepository.findById(itemOrderId);

        if(itemOrder.isPresent())
        {
            if(itemOrder.get().getKitchenItemStatus().equals("PENDING"))
            {
                itemOrder.get().setKitchenItemStatus("INPROGRESS");

            } else if (itemOrder.get().getKitchenItemStatus().equals("INPROGRESS")) {
                itemOrder.get().setKitchenItemStatus("COMPLETED");
            } else if (itemOrder.get().getKitchenItemStatus().equals("COMPLETED")) {
                return new ResponseEntity(itemOrder.get(),HttpStatus.OK);
            }

           ItemOrder savedItemOrder = itemOrderRepository.save(itemOrder.get());

            return new ResponseEntity(savedItemOrder,HttpStatus.OK);


        }

        return new ResponseEntity(HttpStatus.OK);


    }
}
