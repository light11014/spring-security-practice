package dev.security.model;

import jakarta.persistence.*;
import lombok.Getter;


// 개별 사용자(User)에 대한 권한 정보를 가지고 있는 테이블
@Entity
@Getter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // 권한 종류(read, write ...)

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
}
