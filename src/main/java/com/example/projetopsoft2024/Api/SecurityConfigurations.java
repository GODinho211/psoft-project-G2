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
                        .requestMatchers(HttpMethod.POST,"/api/books").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST,"/api/books/getAll").hasRole("LIBRARIAN")

                        .requestMatchers(HttpMethod.GET,"/api/lendings/top5readers").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST,"/api/lendings/lend").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST,"/api/lendings/returnBook").hasRole("READER")
                        .requestMatchers(HttpMethod.GET,"/api/lendings/overdueLendings").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/lendings/AvgLendingPerGender").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/lendings/average-lending-duration").hasRole("LIBRARIAN")


                        //UsersEndpoints
                        .requestMatchers(HttpMethod.PUT,"/api/users/{readernumber}").hasRole("READER")
                        .requestMatchers(HttpMethod.GET,"/api/users/readernumber/{readernumber}").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/users/name/{name}").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/users/email/{email}").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/users/books/{readernumber}").hasRole("READER")
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/all").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET, "/api/users/{email}").hasRole("READER")

                        //BooksEndpoints
                        .requestMatchers(HttpMethod.PUT,"/api/books/{bookId}").hasRole("LIBRARIAN")//
                        .requestMatchers(HttpMethod.GET,"/api/books/{bookId}").hasRole("LIBRARIAN")//
                        .requestMatchers(HttpMethod.GET,"/api/gender/search").hasRole("LIBRARIAN")//

                        .requestMatchers(HttpMethod.GET,"/api/picture/{bookId}").hasRole("LIBRARIAN")//

                        .requestMatchers(HttpMethod.POST,"/api/books").hasRole("LIBRARIAN")//
                        .requestMatchers(HttpMethod.GET,"/api/books/title/{title}").hasRole("READER")//
                        .requestMatchers(HttpMethod.GET,"/api/books/top5LentBooks").hasRole("LIBRARIAN")//
                        .requestMatchers(HttpMethod.GET,"/api/books/top5Genders").hasRole("LIBRARIAN")//

                        //Bonus Endpoints/
                        .requestMatchers(HttpMethod.GET,"api/author/{authorName}").hasRole("READER")//

                        .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                        .requestMatchers("/images/**").permitAll()  // Allow access to 'images' directory
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

