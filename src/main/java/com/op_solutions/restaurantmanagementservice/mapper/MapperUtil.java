package com.op_solutions.restaurantmanagementservice.mapper;

import com.op_solutions.restaurantmanagementservice.dto.*;
import com.op_solutions.restaurantmanagementservice.entity.*;
import com.op_solutions.restaurantmanagementservice.service.MenuItemService;
import com.op_solutions.restaurantmanagementservice.service.OrderService;
import com.op_solutions.restaurantmanagementservice.service.RestaurantTableService;
import com.op_solutions.restaurantmanagementservice.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class MapperUtil {


    private final MenuItemService menuItemService;
    private final StaffService staffService;
    private final RestaurantTableService restaurantTableService;

    public Order mapOrderFromInputOrderDTO(OrderInputDTO orderInputDTO)
    {

        System.out.println(orderInputDTO.toString());

        Staff staff = staffService.getStaffById(orderInputDTO.getStaffId()).getBody();

        RestaurantTable restaurantTable = restaurantTableService.getRestaurantTableById(orderInputDTO.getTableNumber()).getBody();

        restaurantTable.setOccupancy("Occupied");

        BigDecimal totalPrice = orderInputDTO.getTotalPrice();

        List<ItemOrder> itemOrderList = orderInputDTO.getItemOrderList().stream().map(io -> mapItemOrderFromItemOrderInputDTO(io)).collect(Collectors.toList());

        return Order.builder()
                .itemsOrderedList(itemOrderList)
                .restaurantTable(restaurantTable)
                .staff(staff)
                .totalPrice(totalPrice)
                .build();
    }

    private ItemOrder mapItemOrderFromItemOrderInputDTO(ItemOrderInputDTO itemOrderInputDTO) {

        MenuItem menuItem = menuItemService.getMenuItemById(itemOrderInputDTO.getMenuItemId()).getBody();
        BigDecimal subTotal = itemOrderInputDTO.getSubTotal();
        Integer quanity = itemOrderInputDTO.getQuantity();
        ItemOrder itemOrder = ItemOrder.builder()
                .menuItem(menuItem)
                .subTotal(subTotal)
                .quantity(quanity)
                .kitchenItemStatus("PENDING")
                .build();
        return itemOrder;

    }

    public OrderKitchenDTO mapOrderToOrderKitchenDTO(Order order)
    {
        System.out.println(order.toString());
        
        List<ItemOrder> itemOrderList = order.getItemsOrderedList();
        
        List<ItemOrderedDTO> itemOrderedListDTOList = itemOrderList.stream().map(itemOrder -> mapItemOrderToItemOrderedDTO(itemOrder)).collect(Collectors.toList());
        
        OrderKitchenDTO orderKitchenDTO = OrderKitchenDTO.builder()
                .orderId(order.getOrderId())
                .staffName(order.getStaff().getStaffName())
                .tableNumber(order.getRestaurantTable().getTableNumber())
                .itemOrders(itemOrderedListDTOList)
                .OverAllStatus(getStatusFromItemOrderedList(itemOrderedListDTOList))
                .build();
        return orderKitchenDTO;
    }

    private String getStatusFromItemOrderedList(List<ItemOrderedDTO> itemOrderedListDTOList) {

        boolean statusCompleted = false;

        List<ItemOrderedDTO> completedOrderList = itemOrderedListDTOList.stream().filter(item -> item.getKitchenItemStatus().equals("COMPLETED")).collect(Collectors.toList());

        if(itemOrderedListDTOList != null && completedOrderList.size() == itemOrderedListDTOList.size())
        {
            return "COMPLETED";
        }

        return "PENDING";


    }

    private ItemOrderedDTO mapItemOrderToItemOrderedDTO(ItemOrder itemOrder) {
            return ItemOrderedDTO.builder()
                    .itemOrderId(itemOrder.getItemOrderId())
                    .category(itemOrder.getMenuItem().getCategory())
                    .itemName(itemOrder.getMenuItem().getItemName())
                    .ingredients(itemOrder.getMenuItem().getIngredients())
                    .kitchenItemStatus(itemOrder.getKitchenItemStatus())
                    .quantity(itemOrder.getQuantity())
                    .build();
    }


}
