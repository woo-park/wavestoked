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
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ITEM_ID")
    private long id;


//   Thorben Janssen -> So, better make sure that all of your to-one associations set the FetchType to LAZY.
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

//   Thorben Janssen -> So, better make sure that all of your to-one associations set the FetchType to LAZY.
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ORD_ID", nullable = false)
    private Ord ord;

    private int orderPrice;
    private int count;

}
