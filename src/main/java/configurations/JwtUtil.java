package configurations;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	 private String secret = "18bedbcbb6f478bb392587c7cf78cd4b1f82172ed80d99b3c42b9ee85fdd2e3f01952445dcaf1d03108a86ce946a019b5751175c2e4ddb29e41509ee92b3b8e5"; // Use a strong key and store it securely
	    private long validity = 3600000; // 1 hour

	    public String generateToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(username)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + validity))
	                .signWith(SignatureAlgorithm.HS256, secret)
	                .compact();
	    }

	    public boolean validateToken(String token, String username) {
	        String extractedUsername = extractUsername(token);
	        return (extractedUsername.equals(username) && !isTokenExpired(token));
	    }

	    public String extractUsername(String token) {
	        return extractAllClaims(token).getSubject();
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	    }

	    private boolean isTokenExpired(String token) {
	    	return extractAllClaims(token).getExpiration().toInstant().isBefore(java.time.Instant.now());
	    }
	
}
