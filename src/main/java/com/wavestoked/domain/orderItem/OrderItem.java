package com.wavestoked.domain.orderItem;


import com.wavestoked.domain.item.Item;
import com.wavestoked.domain.ord.Ord;
//import com.wavestoked.domain.ord.Order;
//import com.wavestoked.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORD_ID", nullable = false)
    private Ord ord;

    private int orderPrice;
    private int count;

}
