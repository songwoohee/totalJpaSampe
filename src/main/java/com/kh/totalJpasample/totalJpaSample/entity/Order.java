package com.kh.totalJpasample.totalJpaSample.entity;

import com.kh.totalJpasample.totalJpaSample.constant.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue // 기본 생성 전략은 Auto
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "order")  // mappedBy 연관 관계의 주인이 아님을 표시
    private List<OrderItem> orderItemList = new ArrayList<>();


}
