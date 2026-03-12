package dev.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

// DB에 저장할 사용자 정보 테이블
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * 비밀번호 인코딩 처리
     * @param encodedPassword
     */
    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}
