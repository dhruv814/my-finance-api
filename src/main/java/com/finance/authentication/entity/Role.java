package com.finance.authentication.entity;

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
