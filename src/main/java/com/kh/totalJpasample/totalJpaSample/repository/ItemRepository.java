package com.kh.totalJpasample.totalJpaSample.repository;

import com.kh.totalJpasample.totalJpaSample.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// 기본적인 CRUD는 JpaRepository에 이미 정의 되어 있음, 페이징 처리도 포함되어 있음
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemName(String itemName);
    // OR 조건 처리
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);
    // LessThan 조건 처리 : price 변수 보다 작은 상품 데이터 조회 하는 쿼리
    List<Item> findByPriceLessThan(Integer price);
    // OrderBy 로 정렬 하기
    List<Item> findAllByOrderByPriceDesc();
    // JPQL 쿼리 작성 하기 : SQL 유사한 객체 지향 쿼리 언어
    // 실제 sql문이 아니기 때문에 DB에 날리는게 아니라 엔티티에 날리는 것. (Item 을 대문자로 쓴 게 그 이유)
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> priceSorting(@Param("itemDetail")String itemDetail);

    // nativeQuery 사용 하기
    @Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc" ,
    nativeQuery = true)
    List<Item> priceSortingNative(@Param("itemDetail") String itemDetail);

}
