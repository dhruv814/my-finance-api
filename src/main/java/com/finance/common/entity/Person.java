package com.finance.common.entity;

import com.finance.common.dto.Auditable;
import com.finance.common.dto.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author dhruvkumar
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "First Name is required!!")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last Name is required!!")
    private String lastName;

    @Column(name = "dob")
    @NotNull(message = "Date of birth is required!!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "DOB must be in the Past!!")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    @NotBlank(message = "Email is required!!")
    @Email(message = "Email is not valid!!")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone Number is required!!")
    @Size(min = 10, max = 10, message = "Phone Number must be only 10 digits!!")
    private String phoneNumber;

    @Embedded
    private Address address;

//    // one client can have many policies
//    @OneToMany(
//            mappedBy = "client",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER
//    )
//    private List<Policy> policies;
}
