package com.finance.authentication.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finance.common.dto.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author dhruvkumar
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required!!")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required!!")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is required!!")
    @Email(message = "Email is not valid!!")
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
//            message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
