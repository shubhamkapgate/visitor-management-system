package com.visitormanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception{
        http.csrf().disable().cors().disable();

        http.authorizeHttpRequests()
                .requestMatchers("/visitors/register", "/h2-console/**").permitAll()
                .anyRequest().authenticated().and().httpBasic();

        return http.build();
    }



    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
