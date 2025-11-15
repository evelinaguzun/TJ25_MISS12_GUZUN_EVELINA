package com.example.lab4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // dezactivează protecția CSRF pentru simplitate
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // doar /login e accesibil fără autentificare
                        .anyRequest().authenticated()          // restul endpointurilor sunt protejate
                )
                .httpBasic(); // activează autentificarea Basic (user și parolă)

        return http.build();
    }
}
