package com.example.startSpring.config;

import com.example.startSpring.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig
 * --------------
 * This class is the "Security Guard" of our application.
 * It decides:
 * 1. Which URLs are public (anyone can enter).
 * 2. Which URLs are private (need a login).
 * 3. How we check passwords (using the AuthenticationProvider).
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // <--- This enables @PreAuthorize annotations on Controllers!
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (Cross-Site Request Forgery) because we use JWTs, which are immune to this.
            .csrf(csrf -> csrf.disable())

            // Define URL access rules
            .authorizeHttpRequests(auth -> auth
                // 1. Allow everyone to access root, login, register, and health check
                .requestMatchers("/", "/api/auth/**", "/api/health", "/images/**", "/static/**", "/*.html", "/*.ico").permitAll()
                
                // 2. Allow everyone to see the Swagger API documentation
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                // 3. Lock everything else! You must be logged in.
                .anyRequest().authenticated()
            )

            // We don't use "Sessions" (cookies). We use "Stateless" (Tokens).
            // This means the server doesn't remember you. You must show your ID (Token) every time.
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Set our custom login logic
            .authenticationProvider(authenticationProvider)

            // Add our JWT Filter *before* the standard Spring Security filter.
            // We want to check the token first!
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
