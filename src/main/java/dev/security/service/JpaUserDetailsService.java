package dev.security.service;

import dev.security.model.User;
import dev.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// UserDetailsService 인터페이스를 구현한 커스텀 구현체
// JPA를 통해 사용자 정보를 DB에서 조회할 수 있도록 구현
@Slf4j
@Service
// @RequiredArgsConstructor // 생성자 기반 주입 with lombok
public class JpaUserDetailsService implements UserDetailsService {

    // JPA 의존성(EntityManager)
    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        System.out.println("jpaUserDetailsService");
        this.userRepository = userRepository;
    }

    /**
     * Spring Data JPA를 통해 DB에서 사용자 정보 조회, 
     * 클라이언트에게 파라미터로 전달받은 username에 해당하는 사용자만 조회
     * @param username the username identifying the user whose data is required.
     * @return UserDetails 타입의 사용자 객체
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);

        // 1. 사용자한테 전달받은 username을 가지고 DB에서 사용자 조회
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 user 없음"));
        System.out.println("user = " + user);

        // 2. 반환타입이 DB에서 꺼낸 Entity가 user가 아닌 시큐리티에서 사용하는 UserDetails이기 때문에
        // 해당 타입으로 감싸주는 처리(Wrapping)

        // 3. 해당 UserDetails 객체 응답
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }
}
