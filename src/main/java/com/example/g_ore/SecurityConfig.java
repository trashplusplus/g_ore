package com.example.g_ore;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService){

        this.userDetailsService = userDetailsService;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(form -> form.loginPage("/login").permitAll());
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/admin").hasAuthority("ADMIN")
                .requestMatchers("/user").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/passwordEdit").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/main", "/", "/top").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/posts/done").anonymous()
                                .anyRequest().authenticated());
        http.userDetailsService(userDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /*


    @Bean
    DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/g_ore");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("admin");
        return dataSourceBuilder.build();
    }
  */

}
