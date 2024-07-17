package com.finance.authentication.config;

import com.finance.authentication.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author dhruvkumar
 */
@Component
public class JwtTokenProvider {

    // Generate JWT token for user
    public String generateToken(CustomUserDetails userDetails) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + SpringSecurityContext.JWT_EXPIRATION * 1000);

        return Jwts.builder()
                .claim("email", userDetails.getEmail())
                .claim("issuedAt", now)
                .claim("expDate", expirationDate)
                .signWith(SpringSecurityContext.JWT_KEY)
                .compact();
    }

    // Validate JWT token
    public boolean validateToken(String token) {
        if (StringUtils.hasText(token)) {
            try {
                // Parse the JWT token and get claims
                Claims claims = Jwts.parser()
                        .setSigningKey(SpringSecurityContext.JWT_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                Date expirationDate = claims.get("expDate", Date.class);
                System.out.println("Expiration date : " + expirationDate.toString());
                System.out.println("Current date : " + new Date().toString());
                return !expirationDate.before(new Date());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    // Get username from token
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SpringSecurityContext.JWT_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email", String.class);
    }

}
