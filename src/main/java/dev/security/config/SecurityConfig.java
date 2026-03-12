package dev.security.config;

import dev.security.service.JpaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // 폼 로그인 방식 활성화
        http
            .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(regexMatcher("/main")).permitAll()
                    // main 경로에 대해서는 permitAll(), 모든 요청 허용
                    .anyRequest().hasAnyAuthority("READ")

                    // .authenticated() // 그 외 나머지는 인증된 사용자만 접근 가능
            )
            // 로그인 페이지는 누구나 접근 가능하게
            .formLogin(form ->
                    form.loginPage("/custom/login") // 커스텀 로그인 페이지에 접근하기 위한 URI
                            .defaultSuccessUrl("/products", true)
                            // -> 로그인 성공했을 때 리다이렉트할 경로?
                            .loginProcessingUrl("/custom/login")
                            // -> 로그인 과정에서 아이디/비밀번호 검증용 URI

                            .permitAll() // 로그인 페이지는 누구나 접근 가능하도록
            );


        return http.build();
    }

    // 패스워드 인코더 등록(BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
