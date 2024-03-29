package com.finance.authentication.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtGeneratorTokanFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//
//            SecretKey key = Keys.hmacShaKeyFor(SpringSecurityContext.JWT_KEY.getBytes());
//            String jwt = Jwts.builder()
//                    .setIssuer("Vinay")
//                    .setSubject("JWT Token")
//                    .claim("username", authentication.getName())
//                    .claim("authorities", convertAuthWithString(authentication.getAuthorities()))
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(new Date().getTime() + 30000000))
//                    .signWith(key).compact();
//
//            response.setHeader(SpringSecurityContext.JWT_HEADER, jwt);
//
//        }
//
//        filterChain.doFilter(request, response);

    }

    private String convertAuthWithString(Collection<? extends GrantedAuthority> collection) {
        Set<String> auth = new HashSet<>();

        for (GrantedAuthority gran : collection) {
            auth.add(gran.getAuthority());
        }

        return String.join(",", auth);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/signIn");
    }

}
