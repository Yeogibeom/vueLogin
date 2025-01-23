package example.vuelogin.config;

import example.vuelogin.jwt.JwtFilter;
import example.vuelogin.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) ->
                authorize.requestMatchers("/**").permitAll()
        );
        http.addFilterBefore(new JwtFilter(), ExceptionTranslationFilter.class);
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).clearAuthentication(true).logoutSuccessHandler(logoutSuccessHandler()));
        return http.build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            // 세션 무효화
            request.getSession().invalidate();

            // 인증 정보 초기화
            SecurityContextHolder.clearContext();

            // 쿠키 삭제
            response.addHeader("Set-Cookie", "jwt=; Max-Age=0; path=/; HttpOnly; SameSite=Strict");

            response.setStatus(200);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"로그아웃 성공\"}");
            System.out.println("로그아웃 성공");
        };

    }
}
