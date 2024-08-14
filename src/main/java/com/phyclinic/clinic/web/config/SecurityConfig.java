package com.phyclinic.clinic.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
private final JwtFilter jwtFilter;
    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*allow all
        http.authorizeHttpRequests()
                .anyRequest()
                .permitAll();
        return http.build();*/

        http
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/patients/**").hasAnyRole("ADMIN","CUSTOMER")
                //.requestMatchers(HttpMethod.GET, "/api/clinicservices/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/clinicservices/**").hasAnyRole("ADMIN","CUSTOMER")
                .requestMatchers(HttpMethod.POST,"/api/clinicservices/").hasRole("ADMIN")
               // .requestMatchers(HttpMethod.PUT).denyAll()
                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .requestMatchers("/api/invoices/random").hasAuthority("random_order")
                .requestMatchers("/api/invoices/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                //.httpBasic(); removed after add our JWTFiler

        return http.build();
    }
/*
Create in memory users
@Bean

    public UserDetailsService memoryUsers(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin") )
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
            .username("customer")
            .password(passwordEncoder().encode("customer123") )
            .roles("CUSTOMER")
            .build();
        return new InMemoryUserDetailsManager(admin,customer);
    }*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{
        return  configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
