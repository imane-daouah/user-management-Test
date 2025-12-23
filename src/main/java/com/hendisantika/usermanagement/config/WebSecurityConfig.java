package com.hendisantika.usermanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Project : user-management
 * Compatible Spring Boot 3 / Spring Security 6
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final String[] PUBLIC_LINK = {
            "/include/**", "/css/**", "/icons/**", "/img/**",
            "/js/**", "/layer/**", "/static/**"
    };

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(bCryptPasswordEncoder);
        return auth;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(PUBLIC_LINK).permitAll()
                        .requestMatchers("/", "/index", "/signup", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/userForm", true)
                        .failureUrl("/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")              // ✅ REMPLACE AntPathRequestMatcher
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
