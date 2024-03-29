package com.finance.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author dhruvkumar
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    
    private CustomUserDetails user;
    private String token;

}
