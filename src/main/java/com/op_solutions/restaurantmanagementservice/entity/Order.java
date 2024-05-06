package com.op_solutions.restaurantmanagementservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ORDER_M")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "staffId")
    private Staff staff;

    @ManyToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "tableNumber")
    private RestaurantTable restaurantTable;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemOrder> itemsOrderedList;

    private BigDecimal totalPrice;

    private String overAllStatus;

}
