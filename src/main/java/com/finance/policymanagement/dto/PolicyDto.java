package com.finance.policymanagement.dto;

import com.finance.common.dto.Auditable;
import com.finance.dto.ClaimDto;
import com.finance.policymanagement.entity.LifeInsured;
import com.finance.policymanagement.entity.PolicyHolder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyDto extends Auditable {

    // policy info 
    private String id;
    private String policyNumber;
    private String policyType;
    private PolicyStatus policyStatus;
    private Integer policyTerm;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private BigDecimal baseSumAssured;

    // premium info
    private BigDecimal premiumAmount;
    private String premiumFrequency;
    private Integer premiumPayingTerm;
    private LocalDate nextPremiumDueDate;

    // policy holder info
    private PolicyHolder policyHolder;

    // life insured info
    private LifeInsured lifeInsured;

    // claim info
    private Set<ClaimDto> claims;
}
