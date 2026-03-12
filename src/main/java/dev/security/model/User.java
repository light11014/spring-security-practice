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

    private String username;
    private String password;

    // 개별 사용자는 2개 이상의 권한을 가질 수 있음(ex. 읽기(READ), 쓰기(WRITE)..)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;

    /**
     * 비밀번호 인코딩 처리
     * @param encodedPassword
     */
    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}
