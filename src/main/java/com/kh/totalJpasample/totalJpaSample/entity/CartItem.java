package com.kh.totalJpasample.totalJpaSample.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne // 하나의 장바구니에는 여러개의 상품을 담을 수 있음(다대일 관계)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item; // 하나의 아이템은 여러 장바구니 상품으로 담길 수 있음 (다대일 관계_

    private int count;

}
