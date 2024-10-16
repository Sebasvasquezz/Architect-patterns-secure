package escuelaing.edu.co.patrones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application.
 * Defines how HTTP requests, login, and logout will be managed.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application, setting up authorization rules, 
     * session management, and exception handling for redirection when access is denied.
     * 
     * This method defines:
     * - Publicly accessible endpoints that do not require authentication.
     * - Endpoints that require authentication to access.
     * - Stateless session management to ensure no session is created or maintained.
     * - Custom handling of authentication errors, redirecting unauthorized users to the login page.
     * 
     * @param http The HttpSecurity object used to configure security features.
     * @return SecurityFilterChain A configured security filter chain for the application.
     * @throws Exception Throws a general exception if any configuration fails.
     */
    @SuppressWarnings({ "removal", "deprecation" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  
            .authorizeRequests()
            .requestMatchers("/api/users/**", "/login.html", "/register.html", "/user.js", "/style.css", "/index.html", "/properties/**").permitAll()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
        return http.build();
    }


    /**
     * Defines the PasswordEncoder bean, which will be used for encoding and verifying passwords.
     * BCrypt is a strong hashing algorithm commonly used for password encryption.
     * 
     * @return PasswordEncoder A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
