package com.finance.common.dto;

import com.finance.policymanagement.entity.Policy;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author dhruvkumar
 */
@Getter
@Setter
public class PersonDto extends Auditable {

    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private Address address;
//
//    // one client can have many policies
//    private List<Policy> policies;
}
