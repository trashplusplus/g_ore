package com.example.g_ore;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(form -> form.loginPage("/login").permitAll());
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/main", "/", "/top").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/posts/done").anonymous()
                                .anyRequest().authenticated());

        return http.build();
    }


    

}
