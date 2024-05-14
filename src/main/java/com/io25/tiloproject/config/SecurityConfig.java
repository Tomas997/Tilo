package com.io25.tiloproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.io25.tiloproject.services.TiloUsersDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableTransactionManagement
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new TiloUsersDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(
                        headers ->
                                headers
                                        .frameOptions(
                                                HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                        )
                                        .cacheControl(cache ->
                                                cache.disable()
                                        )
                )
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        //.anyRequest().authenticated()
                                        //.requestMatchers("/secured/**").authenticated()
                                        .requestMatchers("/**").permitAll()
                )
                .sessionManagement(
                        session ->
                                session
                                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .formLogin(
                        login ->
                                login
                                        .permitAll()
                                        .successForwardUrl("/home")
                )
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .build();
    }
//
//    @Bean
//    public SecurityFilterChain securityLogoutFilterChain(HttpSecurity http) throws Exception {
//        http.logout((logout) ->
//                logout.deleteCookies("remove")
//                        .invalidateHttpSession(false)
//                        .logoutUrl("/custom-logout")
//                        .logoutSuccessUrl("/logout-success")
//        );
//        return http.build();
//    }

}
