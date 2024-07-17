package com.finance.policymanagement.entity;

import com.finance.common.dto.Auditable;
import com.finance.policymanagement.dto.PolicyStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
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
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "policy")
public class Policy extends Auditable {

    // POLICY INFO
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "policy_name")
    @NotBlank(message = "Policy Name is required")
    private String policyName;

    @Column(name = "policy_insurer_name")
    @NotBlank(message = "Policy Insurer Name is required")
    private String policyInsurerName;

    @Column(name = "policy_number", unique = true)
    @NotBlank(message = "Policy number is required")
    private String policyNumber;

    @Column(name = "policy_type")
    @NotBlank(message = "Policy Type is required")
    private String policyType;

    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;

    @Column(name = "policy_term")
    private Integer policyTerm;

    @Column(name = "policy_start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate policyStartDate;

    @Column(name = "policy_end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate policyEndDate;

    @Column(name = "base_sum_assured")
    private BigDecimal baseSumAssured;

    // PREMIUM INFO
    @Column(name = "premium_amount")
    @NotNull(message = "Premium Amount is required")
    private BigDecimal premiumAmount;

    @Column(name = "premium_frequency")
    private String premiumFrequency;

    @Column(name = "premium_paying_term")
    private Integer premiumPayingTerm;

    @Column(name = "premium_due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiumDueDate;

    @Column(name = "fund_value")
    private BigDecimal fundValue;

    // POLICY HOLDER
    // policies can have only one policy holder
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "policy_holder_id", referencedColumnName = "id", nullable = false)
//    private PolicyHolder policyHolder;

    // LIFE INSURED 
    @Column(name = "insured_person_name")
    @NotBlank(message = "Insured Person Name is required")
    private String insuredPersonName;

    @Column(name = "insured_person_dob")
    @NotNull(message = "Date of birth is required!!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "DOB must be in the Past!!")
    private LocalDate insuredPersonDateOfBirth;

    @Column(name = "email")
    @NotBlank(message = "Email is required!!")
    @Email(message = "Email is not valid!!")
    private String emailAddress;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone Number is required!!")
    @Size(min = 10, max = 10, message = "Phone Number must be only 10 digits!!")
    private String phoneNumber;

    @Column(name = "relationship_to_insured")
    private String relationshipToTheInsured;

    // CLAIMS
    // policy has multiple claims
    @OneToMany(mappedBy = "policy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Claim> claims;

}
