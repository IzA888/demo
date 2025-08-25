package com.example.demo.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.model.UserModel;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.UserService;
import com.example.demo.security.JwtAuthFilter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    // Inject your user service here (make sure UserService and UserModel are defined in your project)
    @Autowired
    private UserService userService;

    private final JwtUtil jwtUtil = new JwtUtil();
    
    @Bean
    public JwtAuthFilter JwtAuthFilter(UserService userService, JwtUtil jwtUtil) {
        return new JwtAuthFilter(userService, jwtUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/user").permitAll() // Allow access to /user without authentication
                .requestMatchers("/login").permitAll() // Allow access to /login without authentication
                .requestMatchers("/tarefa/**").authenticated() // Require authentication for /tarefa/**
                .requestMatchers("/user/**").authenticated() // Require authentication for /user/**
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(JwtAuthFilter(userService, jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserModel user = userService.loadUserByUsername(username);
                if (user != null) {
                    return User.withUsername(user.getUsername())
                               .password(user.getPassword())
                               //.roles("USER") // Assign roles as needed
                               .build();
                } else {
                    throw new UsernameNotFoundException("User not found");
                }
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}