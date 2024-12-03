package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	public class SecurityConfig {

	    private final JwtRequestFilter jwtRequestFilter;

	    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
	        this.jwtRequestFilter = jwtRequestFilter;
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF protection for API
	            .authorizeRequests(auth -> auth
	                .requestMatchers("/api/User/register", "/api/auth/login").permitAll() // Allow registration and login without authentication
	                .anyRequest().authenticated() // Require authentication for all other endpoints
	            )
	            .sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session management
	            )
	            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
	}

	
}
