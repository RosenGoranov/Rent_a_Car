package com.example.Rent_a_Car.config;


import com.example.Rent_a_Car.model.enums.RoleEnum;
import com.example.Rent_a_Car.repository.UserRepository;
import com.example.Rent_a_Car.services.ApplicationUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain webFilterChain(HttpSecurity http,
                                              SecurityContextRepository securityContextRepository) throws Exception {
        http
                .authorizeHttpRequests(
                        authorize ->
                                authorize.
                                        requestMatchers("/static/**", "/css/**", "/favicon/**", "/images/**", "/js/**")
                                        .permitAll()
                                        .requestMatchers("/", "/auth/login", "/auth/register", "/auth/login-error","/cars/**")
                                        .permitAll().
                                        requestMatchers("/user**").hasRole(RoleEnum.USER.name()).
                                        requestMatchers("/employee/**").hasRole(RoleEnum.EMPLOYEE.name()).
                                        requestMatchers("/moderator/**").hasRole(RoleEnum.MODERATOR.name()).
                                        requestMatchers("/admin/**").hasRole(RoleEnum.ADMIN.name()).
                                        anyRequest().authenticated()
                )
                .formLogin(
                        (formLogin) ->
                                formLogin.
                                        loginPage("/login").
                                        usernameParameter("email").
                                        passwordParameter("password").
                                        defaultSuccessUrl("/", true).
                                        failureForwardUrl("/login-error")
                )
                .logout((logout) ->
                        logout.logoutUrl("/logout").
                                logoutSuccessUrl("/").
                                invalidateHttpSession(true)
                )
                .securityContext(
                        securityContext -> securityContext.
                                securityContextRepository(securityContextRepository)
                )
                .sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return new ApplicationUserDetailsService(userRepository);

    }


}

