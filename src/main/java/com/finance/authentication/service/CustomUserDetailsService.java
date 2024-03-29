package com.finance.authentication.service;

import com.finance.authentication.dto.CustomUserDetails;
import com.finance.authentication.entity.User;
import com.finance.authentication.repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dhruvkumar
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public CustomUserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElse(null);
        if (user != null) {
            return CustomUserDetails.builder()
                    .userId(user.getId())
                    .username(user.getFirstName() + " " + user.getLastName())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .authorities(Collections.emptyList())
                    .build();

        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
