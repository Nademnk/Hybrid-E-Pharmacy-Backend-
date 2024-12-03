package configurations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import exceptions.JwtTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
    private JwtUtil jwtUtil;

	 

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	        // Extract JWT and validate
	        String authorizationHeader = request.getHeader("Authorization");
	        String username = null;
	        String jwt = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            jwt = authorizationHeader.substring(7);
	            try {
	                username = jwtUtil.extractUsername(jwt);
	            } catch (Exception e) {
	                throw new JwtTokenException("Invalid or expired token");
	            }
	        }

	        // If valid token, set user authentication in context
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            // Set authentication in context
	        }

	        chain.doFilter(request, response);
	    }
	
}
