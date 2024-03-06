package com.example.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Profile("azure-enabled")
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
//            .cors(withDefaults())
//            .csrf(AbstractHttpConfigurer::disable)
//            .formLogin(AbstractHttpConfigurer::disable)
            .oauth2Login(withDefaults());

        return http.build();
    }

    @Bean
    @Profile("!azure-enabled")
    public SecurityFilterChain azureDisabledFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(withDefaults());

        return httpSecurity.build();
    }

}
