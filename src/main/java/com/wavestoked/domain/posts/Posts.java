package com.wavestoked.domain.posts;
import com.wavestoked.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {  // inherits Time class  // 실제 DB의 Table과 매칭되는 class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 생성일 추가 코드 예제 -> instead, we can audit with BaseTimeEntity class
    /*
    public void savePosts() {
        ...
        posts.setCreateDate(new LocalDate());
        postsRepository.save(posts);
        ...
    }
    */

//    public void save(String title, String content, String author) {
//        this.title = title;
//        this.content = content;
//        this.author = author;
//    }


//    @Transactional
//    public Order cancelOrder(int orderId) {
//        Orders order = ordersRepository.findById(orderId);
//        Billing billing = billingRespository.findByOrderId(orderId);
//        Delivery delivery = deliveryRepository.findByOrderId(orderId);
//
//        delivery.cancel();
//
//        order.cancel();
//        billing.cancel();
//
//        return order;
//    }
}


