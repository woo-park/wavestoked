package com.wavestoked.domain.item;

import com.wavestoked.domain.orderItem.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private long id;

    private String name;
    private int price;
    private int stockQuantity;



}
