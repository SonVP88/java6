package com.example.jv6.config;

import jakarta.persistence.Id;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService authentication() {

        PasswordEncoder encoder = passwordEncoder();

        List<UserDetails> users = new ArrayList<>();
        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();
        users.add(user);

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        users.add(admin);

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public SecurityFilterChain authorization(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/role/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                .loginProcessingUrl("/login"))
//                .logout(logout -> logout
//                        .logoutUrl("/login"))
        ;
        return httpSecurity.build();
    }


}
