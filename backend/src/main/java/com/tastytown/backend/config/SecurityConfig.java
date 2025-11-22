package com.tastytown.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.tastytown.backend.security.CustomUserDetailsService;
import com.tastytown.backend.security.jwt.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger/OpenAPI
                        .requestMatchers("/v1/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Public GET endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/foods/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/catagories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/images/**").permitAll()

                        // Authentication endpoints (public)
                        .requestMatchers("/api/v1/auth/**").permitAll() // includes login, register, refresh

                        // User-only endpoints
                        .requestMatchers("/api/v1/cart/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/orders/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/orders/user").authenticated()

                        // Admin-only endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register-admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/foods/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/foods/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/foods/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/catagories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/catagories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/catagories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/orders").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/orders/**").hasRole("ADMIN")

                        // Any other endpoint requires authentication
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
                                CustomUserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
