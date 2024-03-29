package com.finance.authentication.config;

import com.finance.authentication.dto.CustomUserDetails;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author dhruvkumar
 */
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        // Assuming your user details implement UserDetails and have a method to get user ID
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            System.out.println("principal is an instance of User details :- " + customUserDetails.getUserId());
            return Optional.of(customUserDetails.getUserId());
        } else {
            // Handle case where principal is not UserDetails (e.g., anonymous user)
            System.out.println("principal is not an instance of User details");
            return Optional.empty();
        }
    }
}
