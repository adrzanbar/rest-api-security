package com.uncode.rest_api_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uncode.rest_api_security.service.CustomUserDetailsService;
import com.uncode.rest_api_security.util.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enables method-level security
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // For encoding passwords
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Provides authentication manager
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/register", "/api/login").permitAll() // Public access to register/login
                        .requestMatchers(HttpMethod.GET, "/api/***").hasAnyRole("ADMIN", "USER") // Allow GET ADMIN and USER
                        .requestMatchers(HttpMethod.POST, "/api/***").hasRole("ADMIN") // Only ADMIN can POST
                        .requestMatchers(HttpMethod.PUT, "/api/***").hasRole("ADMIN") // Only ADMIN can PUT
                        .requestMatchers(HttpMethod.DELETE, "/api/***").hasRole("ADMIN") // Only ADMIN can DELETE
                        .anyRequest().authenticated()) // All other requests require authentication
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // No session is created

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build(); // Build the security filter chain
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(jwtUtil, customUserDetailsService); // Provide JWT request filter
    }
}
