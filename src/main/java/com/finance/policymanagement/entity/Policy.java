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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @NotBlank(message = "Premium Amount is required")
    private BigDecimal premiumAmount;

    @Column(name = "premium_frequency")
    private String premiumFrequency;

    @Column(name = "premium_paying_term")
    private Integer premiumPayingTerm;

    @Column(name = "next_premium_due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextPremiumDueDate;

    // POLICY HOLDER
    // policies can have only one policy holder
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "policy_holder_id", referencedColumnName = "id", nullable = false)
    private PolicyHolder policyHolder;

    // LIFE INSURED 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "life_insured_id", referencedColumnName = "id")
    private LifeInsured lifeInsured;

    // CLAIMS
    // policy has multiple claims
    @OneToMany(mappedBy = "policy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Claim> claims;

}
