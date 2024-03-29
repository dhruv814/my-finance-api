package com.finance.authentication.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokanValidatorFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("Path -> " + request.getServletPath());
        String jwt = getTokenFromRequest(request);
        System.out.println("Jwt -> " + jwt);

        if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
            // Parse the JWT token and get the claims
            Claims claims = Jwts.parser()
                    .setSigningKey(SpringSecurityContext.JWT_KEY)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            String email = claims.get("email", String.class);
            System.out.println("Email -> " + email);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return request.getServletPath().equals("/register");
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(SpringSecurityContext.JWT_HEADER);

        if (token != null) {
            if (token.startsWith("Bearer ")) {
                // "Bearer " is 7 characters long
                return token.substring(7);
            }
            return token;
        }
        return null;
    }

}
