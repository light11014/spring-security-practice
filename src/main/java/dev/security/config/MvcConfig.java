package dev.security.config;

// Spring MVC와 관련된 설정

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring Boot 이전에는 .xml 파일에 설정을 구성할 수 있었음
// Boot 이후에는 추상화되다보니까 필요에 따라 별도의 클래스를 통해 설정할 수도 있음
// -> WebMvcConfigurer

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /main으로 요청하면 index.html로 응답하도록
        registry.addViewController("/main").setViewName("index");

        // /custom/login 경로로 요청하면 login.html로 응답
        registry.addViewController("/custom/login").setViewName("login");

        /// ....
    }
}

// 리다이렉트의 경우??
// registry.addRedirectViewController() -> 리다이렉트 처리용
