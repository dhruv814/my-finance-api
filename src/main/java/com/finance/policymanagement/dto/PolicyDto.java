package com.finance.policymanagement.dto;

import com.finance.common.dto.Auditable;
import com.finance.dto.ClaimDto;
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
    private String policyInsurerName;
    private String policyName;
    private String policyType;
    private PolicyStatus policyStatus;
    private Integer policyTerm;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    
    // premium info
    private BigDecimal premiumAmount;
    private String premiumFrequency;
    private Integer premiumPaymentTerms;
    private LocalDate premiumDueDate;
    private BigDecimal baseSumAssured;
    private BigDecimal fundValue;

    // life insured info
    private String insuredPersonName;
    private LocalDate insuredPersonDateOfBirth;
    private String phoneNumber;
    private String emailAddress;
    private String relationshipToTheInsured;

    // claim info
    private Set<ClaimDto> claims;
   
    
}
