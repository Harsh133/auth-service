package com.example.auth_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth         
                .antMatchers("/auth/**").permitAll()           
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()    
                .antMatchers("/auth/login", "/auth/register", "/hello").permitAll()
        	    .antMatchers("/actuator/**").permitAll() 
        	    .antMatchers("/greeting/**").permitAll()
        	    .antMatchers("/test/**").permitAll() 
        	    .antMatchers("/user/**").permitAll() 
                .anyRequest().authenticated()
            )
            //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // make sure JWT filter runs before UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
//    @Bean
//    public SecurityFilterChain securityFilterChainn(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//            	    .antMatchers("/auth/login", "/auth/register", "/hello").permitAll()
//            	    .antMatchers("/actuator/**").permitAll() // allow health
//            	    .antMatchers("/greeting/**").permitAll()
//            	    .antMatchers("/test/**").permitAll() // optional: allow your test endpoints
//            	    .antMatchers("/user/**").permitAll() // optional: allow your test endpoints
//            	    .anyRequest().authenticated()
//            	)
//            .sessionManagement(session -> 
//                session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//            );
//
//        return http.build();
//    }
//    
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//            User.withUsername("admin")   
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build()
//        );
//    }

}
