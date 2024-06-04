package com.example.projetopsoft2024.Api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws  Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/lendings/lend").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST,"/api/lendings/returnBook").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST,"/api/books").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST,"/api/books/getAll").hasRole("LIBRARIAN")

                        //UsersEndpoints
                        .requestMatchers(HttpMethod.PUT,"/api/users/{readernumber}").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/users/readernumber/{readernumber}").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/users/name/{name}").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/users/books/{readernumber}").hasRole("READER")
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()


                        .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
