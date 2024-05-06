package com.op_solutions.restaurantmanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrderedDTO {

    private Long itemOrderId;
    private String itemName;
    private String ingredients;
    private Integer quantity;
    private String category;
    private String kitchenItemStatus;
}
