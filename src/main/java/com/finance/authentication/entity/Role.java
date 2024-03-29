package com.finance.authentication.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author dhruvkumar
 */
public enum Role {
    USER,
    ADMIN;
//    
//    // Method to convert Role enum to a collection of GrantedAuthority
//    public Collection<? extends GrantedAuthority> toAuthorities() {
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority(this.name()));
//        return Collections.unmodifiableSet(authorities);
//    }
}
