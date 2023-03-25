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
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/main", "/").permitAll()
                        .requestMatchers("/posts/**").authenticated()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/register").anonymous()
                        .requestMatchers(HttpMethod.POST, "/posts/done").anonymous()
                                .anyRequest().authenticated());
        http.formLogin(form -> form.loginPage("/login").permitAll());
        return http.build();
    }

    /*
    @Bean
    public UserDetailsService userDetails(){
        UserDetails user = User.builder()
                .username("0x3")
                .password("123")
                .roles("admin").build();


        return new InMemoryUserDetailsManager(user);
    }

     */

}
