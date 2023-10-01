package com.example.Rent_a_Car.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.Rent_a_Car.model.enums.RoleEnum.ADMIN;
import static com.example.Rent_a_Car.model.enums.RoleEnum.USER;
import static com.example.Rent_a_Car.model.enums.UserPermissionEnum.*;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/css/**","/pics/**","/js/**")
                                .permitAll()
                                .requestMatchers("/api/v1/auth/**", "/", "/index", "/auth/**")
                                .permitAll()
                                .requestMatchers("/api/v1/home/user/**").hasRole(USER.name())
                                .requestMatchers(HttpMethod.POST, "/api/v1/home/admin/**")
                                .hasAuthority(CREATE.name())
                                .requestMatchers(HttpMethod.PUT, "/api/v1/home/admin/**")
                                .hasAuthority(UPDATE.name())
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/home/admin/**")
                                .hasAuthority(UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/home/admin/**")
                                .hasAuthority(DELETE.name())
                                .requestMatchers(HttpMethod.GET, "/api/v1/home/admin/**")
                                .hasAuthority(READS.name())
                                .requestMatchers("api/v1/home/admin/**", "/**")
                                .hasRole(ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                ).logout((logout) ->
                        logout
                                .deleteCookies()
                                .invalidateHttpSession(true)
                                .logoutUrl("/index"))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
