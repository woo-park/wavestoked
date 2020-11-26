package com.wavestoked.domain.ord;


import com.wavestoked.domain.member.Member;
import com.wavestoked.domain.orderItem.OrderItem;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Ord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORD_ID")
    @NotNull
    private long id;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Column(name="ORD_NAME")
    private String ordName;

    @OneToMany(mappedBy = "ord", targetEntity = OrderItem.class)//, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Column(name="ORD_DATE")   // H2인지 뭔지가 이름을 UPPER _ CASE밖에 읽질 못하네
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordDate;

//    @Enumerated(EnumType.STRING)
//    private OrdStatus status;

//    public void setMember(Member member) {
//        if(this.member != null) {
//            this.member.getOrders().remove(this);
//            this.member = member;
//            member.getOrders().add(this);
//        }
//    }
//
//    public void addOrderItem(OrderItem orderItem) {
//        orderItems.add(orderItem);
//        orderItem.setOrd(this);
//
//    }


    public enum OrdStatus {
        ORDER, CANCEL
    }
}

