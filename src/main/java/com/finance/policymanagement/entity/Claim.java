package com.finance.policymanagement.entity;

import com.finance.policymanagement.dto.ClaimStatus;
import com.finance.policymanagement.entity.Policy;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "claim_number")
    private String claimNumber;

    @Column(name = "descr")
    private String descr;

    @Column(name = "claim_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    private ClaimStatus claimStatus;
    
    // Claims can be linked to only one policy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "policy_id", referencedColumnName = "id")
    private Policy policy;

}
