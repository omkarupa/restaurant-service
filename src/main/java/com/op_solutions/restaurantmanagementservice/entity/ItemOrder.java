package com.op_solutions.restaurantmanagementservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ITEMORDER_M")
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemOrderId;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    private Integer quantity;
    private BigDecimal subTotal;
    private String kitchenItemStatus; // set default Pending // Working Progree // Completed

}
