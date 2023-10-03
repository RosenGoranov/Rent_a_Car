package com.example.Rent_a_Car.config;


import com.example.Rent_a_Car.model.entity.User;
import com.example.Rent_a_Car.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.ArrayList;
import java.util.List;

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
        ;
    }

//    @Bean
//    @Order(1)
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorizeHttpRequests) ->
//                        authorizeHttpRequests
//
//                                .requestMatchers("/css/**", "/pics/**", "/js/**")
//                                .permitAll()
//                                .requestMatchers("/api/v1/auth/**", "/", "/index")
//                                .permitAll()
//                                .requestMatchers("/api/v1/home/user/**").hasAuthority(USER.name())
//                                .requestMatchers(HttpMethod.POST, "/api/v1/home/admin/**")
//                                .hasAuthority(CREATE.name())
//                                .requestMatchers(HttpMethod.PUT, "/api/v1/home/admin/**")
//                                .hasAuthority(UPDATE.name())
//                                .requestMatchers(HttpMethod.PATCH, "/api/v1/home/admin/**")
//                                .hasAuthority(UPDATE.name())
//                                .requestMatchers(HttpMethod.DELETE, "/api/v1/home/admin/**")
//                                .hasAuthority(DELETE.name())
//                                .requestMatchers(HttpMethod.GET, "/api/v1/home/admin/**")
//                                .hasAuthority(READS.name())
//                                .requestMatchers("api/v1/home/admin/**", "/**")
//                                .hasRole(ADMIN.name())
//                                .anyRequest()
//                                .authenticated()
//                )
//                .sessionManagement((sessionManagement) ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                ).logout((logout) ->
//                        logout
//                                .logoutUrl("/logout")
//                                .deleteCookies()
//                                .invalidateHttpSession(true)
//                                .logoutSuccessUrl("/auth/login"))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//
//    }

    @Bean
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/css/**", "/pics/**", "/js/**")
                                .permitAll()
                                .requestMatchers("/", "/index","/auth/**")
                                .permitAll()
                                .requestMatchers("/home").hasRole(USER.name())
                                .requestMatchers(HttpMethod.POST, "/admin")
                                .hasAuthority(CREATE.name())
                                .requestMatchers(HttpMethod.PUT, "/admin/**")
                                .hasAuthority(UPDATE.name())
                                .requestMatchers(HttpMethod.PATCH, "/admin/**")
                                .hasAuthority(UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/admin/**")
                                .hasAuthority(DELETE.name())
                                .requestMatchers(HttpMethod.GET, "/admin/**")
                                .hasAuthority(READS.name())
                                .requestMatchers("/admin/**", "/**")
                                .hasRole(ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .deleteCookies()
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/auth/login")


                );
        return http.build();

    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new ApplicationUserDetailsService(userRepository, userRepository1);
    }

    private class   ApplicationUserDetailsService implements UserDetailsService{
        private final UserRepository userRepository;

        public ApplicationUserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return userRepository.findByEmail(email).map(this::map).
                    orElseThrow(() -> new UsernameNotFoundException("UserEntity with name " + email + " not found!"));
        }

        private UserDetails map(User user) {
            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    extractAuthorities(user)
            );
        }

        private List<GrantedAuthority> extractAuthorities(User user) {
            return new List<>(List.of(user.
                    getRole()));
        }

        private GrantedAuthority mapRole(UserRoleEntity userRoleEntity) {
            return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
        }
    }

}

