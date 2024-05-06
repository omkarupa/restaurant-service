package com.op_solutions.restaurantmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderKitchenDTO {

    private Long orderId;
    private String staffName;
    private Integer tableNumber;
    private List<ItemOrderedDTO> itemOrders;
    private String OverAllStatus;

}
