package com.wavestoked.domain.ord;


import com.wavestoked.domain.member.Member;
import com.wavestoked.domain.orderItem.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
public class Ord {
    @Id
    @GeneratedValue
    @Column(name = "ORD_ID")
    @NotNull
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "ord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

//    @Enumerated(EnumType.STRING)
//    private OrdStatus status;

    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getOrders().remove(this);
            this.member = member;
            member.getOrders().add(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrd(this);

    }


    public enum OrdStatus {
        ORDER, CANCEL
    }
}

