package com.kh.totalJpasample.totalJpaSample.repository;

import com.kh.totalJpasample.totalJpaSample.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// 네이밍 규칙에 의해서 API를 작성하면 그에 맞는 쿼리문을 하이버네이트가 구현 해줌
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);  // optional : null을 허용하지 않는다.
    Member findByPassword(String pwd);
    Member findByEmailAndPassword(String email, String pwd);
    boolean existsByEmail(String email);
}
