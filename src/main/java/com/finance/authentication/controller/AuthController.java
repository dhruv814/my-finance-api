package com.finance.authentication.controller;

import com.finance.authentication.config.JwtTokenProvider;
import com.finance.authentication.dto.CustomUserDetails;
import com.finance.authentication.dto.JwtResponse;
import com.finance.authentication.dto.UserDto;
import com.finance.authentication.service.infc.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public ResponseEntity<Object> getLoggedInUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("No user logged in currently",
                    HttpStatus.OK);
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails customUserDetails) {
            return new ResponseEntity<>(customUserDetails, HttpStatus.OK);
        } else if (principal instanceof UserDetails userDetails) {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            // Handle case where principal is not UserDetails (e.g., anonymous user)
            return new ResponseEntity<>("Anonymous User logged in",
                    HttpStatus.OK);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginSubmit(
            @NotBlank @RequestParam(value = "emailAddress", required = false) String emailAddress,
            @NotBlank @RequestParam(value = "password", required = false) String password) {
        // Here, you would typically validate the username and password
        // against your authentication mechanism (e.g., database, LDAP).
        // For demonstration purposes, let's just print them.
        System.out.println("Username: " + emailAddress);
        System.out.println("Password: " + password);

        // Authenticate user
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(emailAddress, password));
            // Set authentication in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate custom token (e.g., JWT)
            UserDto userDto = userService.getUserByEmail(emailAddress);

            CustomUserDetails user = CustomUserDetails.builder()
                    .userId(userDto.getId())
                    .username(userDto.getFirstName() + " " + userDto.getLastName())
                    .password(userDto.getPassword())
                    .email(userDto.getEmail())
                    .role(userDto.getRole())
                    .authorities(Collections.emptyList())
                    .build();

            String token = jwtTokenProvider.generateToken(user);

            //String customToken = generateCustomToken(authentication);
            // Return custom token to the client
            return ResponseEntity.ok(new JwtResponse(user, token));
        } catch (AuthenticationException ex) {
            // If authentication fails, return unauthorized status
            return new ResponseEntity<>("Invalid username or password",
                    HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<Object> registration(@RequestBody UserDto userDto) {
        UserDto existingUser = userService.getUserByEmail(userDto.getEmail());
        if (existingUser != null
                && existingUser.getEmail() != null
                && !existingUser.getEmail().isEmpty()) {
            return new ResponseEntity<>("There is already an account registered with the same email",
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.saveUser(userDto));

    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate current session
        request.getSession().invalidate();

        // Redirect user to a logout confirmation page or login page
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(
            @PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

}
