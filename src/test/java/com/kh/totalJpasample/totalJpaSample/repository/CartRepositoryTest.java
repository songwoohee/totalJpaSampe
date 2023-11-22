package com.kh.totalJpasample.totalJpaSample.repository;


import com.kh.totalJpasample.totalJpaSample.entity.Cart;
import com.kh.totalJpasample.totalJpaSample.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest  // 스프링 컨텍스트를 로드하여 테스트 환경 설정
@Transactional   // 데이터베이스의 논리적인 작업 단위, 모두 성공이 아니면 롤백,
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j // 기록(로그)을 남기는 용도, 로깅 데이터를 처리하기 위해서 사용


class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;
    @Autowired
     MemberRepository memberRepository;
     @PersistenceContext // JPA의 EntityManager를 주입 받음
    EntityManager em;
     // 회원 엔티티 생성
    public Member createMemberInfo() {
        Member member = new Member();
        member.setUserId("bambi");
        member.setPassword("woohee");
        member.setName("밤비");
        member.setEmail("bambi@google.com");
        member.setRegDate(LocalDateTime.now());
        return member;
    }
    @Test
    @DisplayName("장바구니 회원 매핑 테스트")
    public void findCartAndMemberTest() {
        Member member = createMemberInfo();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setCartName("마켓 컬리 장바구니");
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush(); // 영속성 컨텍스트에 데이터 저장 후 트랜잭션이 끝날 때 데이터베이스에 기록
        em.clear(); // 영속성 컨텍스트를 비움
        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);  // 없으면 예외 처리 해주라
        System.out.println(saveCart);

    }

}