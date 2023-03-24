package com.example.g_ore;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/main", "/", "/posts/**").permitAll()
                        .requestMatchers("/css/**", "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/posts/done").permitAll()
                );
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
