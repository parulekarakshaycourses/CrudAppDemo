package com.example.CrudAppDemo.config;

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
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class MySecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/assets/**").permitAll()
                    .requestMatchers("/emp/**").hasRole("ADMIN")
                    .requestMatchers("/emp/**").hasRole("USER")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .defaultSuccessUrl("/emp/", true)
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll()
                .and()
                    .rememberMe()
                    .key("mySecKey")
                    .tokenValiditySeconds(60 * 60 * 60 * 24 * 7)
                //    .rememberMeParameter("remember_me_param")
        ;

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        List<UserDetails> listUser = new ArrayList<>();

        listUser.add(
                User.builder()
                        .username("admin")
                        .password(getPasswordEncoder().encode("123"))
                        .roles("ADMIN")
                        .build()
        );

        listUser.add(
                User.builder()
                        .username("user")
                        .password(getPasswordEncoder().encode("456"))
                        .roles("USER")
                        .build()
        );

        return new InMemoryUserDetailsManager(listUser);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
