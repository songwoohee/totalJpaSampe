package com.kh.totalJpasample.totalJpaSample.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    // id를 Long 타입으로 만들고 가는게 관례. 사용 하지 않아도 고유의 값으로 가지고 있는게 좋다.
    private Long id;

    private String userId;


    @Column(nullable = false)  // NULL을 허용 하지 않음
    private String name;
    private String password;
    // email은 유니크 타입. 제약 조건을 검.
    @Column(unique = true)  // 유일한 값이어야 함
    private String email;
    private LocalDateTime regDate;
    @PrePersist
    public void PrePersist() {
        regDate = LocalDateTime.now();
    }

}
