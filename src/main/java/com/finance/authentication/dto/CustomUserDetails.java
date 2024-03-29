package com.finance.authentication.dto;

import com.finance.authentication.entity.Role;
import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author dhruvkumar
 */
@Getter
@Setter
@Data
@Builder
public class CustomUserDetails implements UserDetails {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
