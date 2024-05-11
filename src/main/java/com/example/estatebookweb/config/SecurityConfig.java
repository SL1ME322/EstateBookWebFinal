package com.example.estatebookweb.config;

import com.example.estatebookweb.filters.PreAuthenticationFilter;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private  final CustomUserDetailsService userDetailsService;
    @Autowired
    private PreAuthenticationFilter preAuthenticationFilter;
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public AuthenticationManager authenticationManager
            (HttpSecurity httpSecurity, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilter (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
//                .headers(headers ->
//                        headers.xssProtection(
//                                xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
//                        ).contentSecurityPolicy(
//                                cps -> cps.policyDirectives("script-src 'self'")
//                        ))
                .addFilterBefore( preAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class)

                //.csrf((csrf) -> csrf.csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse()))

                .authorizeHttpRequests()
                 //.requestMatchers("/review/**").hasRole("USER")
                //.requestMatchers("/getEstatesOnMainPage","/login","html","/swagger-ui/**","/estates/createEstate","/estates/**","/v3/api-docs/**" ,"/login**","/logout**", "/register**", "/estates/createEstate","/estates/**","/html/**", "/chat", "/addEstate","/editEstate"   ).hasAnyRole("USER"    )
                //.requestMatchers("/swagger-ui/**","/estates/createEstate","/estates/**","/v3/api-docs/**"  ).hasAnyRole("MODERATOR")
                 //.requestMatchers("/**","/getEstatesOnMainPage","/login","html","/swagger-ui/**","/estates/createEstate","/estates/**","/v3/api-docs/**" ,"/login**","/logout**", "/register**", "/estates/createEstate","/estates/**","/review/**").permitAll()
                .anyRequest().permitAll();
        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/html").permitAll() ;
        return httpSecurity.build();
    }
//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder()
//
//                .build();
//    }

    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}