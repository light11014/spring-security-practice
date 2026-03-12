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

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

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
