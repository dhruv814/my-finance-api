package com.finance.policymanagement.service;

import com.finance.policymanagement.dto.PolicyDto;
import com.finance.policymanagement.entity.Policy;

/**
 *
 * @author dhruvkumar
 */
public class PolicyMapper {

    public Policy toEntity(PolicyDto policyDto) {
        if (policyDto == null) {
            return null;
        }

        Policy policy = new Policy();
        policy.setId(policyDto.getId());
        policy.setPolicyName(policyDto.getPolicyName());
        policy.setPolicyNumber(policyDto.getPolicyNumber());
        policy.setPolicyInsurerName(policyDto.getPolicyInsurerName());
        policy.setPolicyStatus(policyDto.getPolicyStatus());
        policy.setPolicyType(policyDto.getPolicyType());
        policy.setPolicyTerm(policyDto.getPolicyTerm());
        policy.setPolicyStartDate(policyDto.getPolicyStartDate());
        policy.setPolicyEndDate(policyDto.getPolicyEndDate());

        // premium info
        policy.setPremiumAmount(policyDto.getPremiumAmount());
        policy.setPremiumFrequency(policyDto.getPremiumFrequency());
        policy.setPremiumPayingTerm(policyDto.getPremiumPaymentTerms());
        policy.setPremiumDueDate(policyDto.getPremiumDueDate());
        policy.setFundValue(policyDto.getFundValue());
        policy.setBaseSumAssured(policyDto.getBaseSumAssured());

        // life assured
        policy.setInsuredPersonName(policyDto.getInsuredPersonName());
        policy.setPhoneNumber(policyDto.getPhoneNumber());
        policy.setEmailAddress(policyDto.getEmailAddress());
        policy.setInsuredPersonDateOfBirth(policyDto.getInsuredPersonDateOfBirth());
        policy.setRelationshipToTheInsured(policyDto.getRelationshipToTheInsured());
        return policy;
    }

    public PolicyDto toDto(Policy policy) {
        if (policy == null) {
            return null;
        }

        PolicyDto policyDto = new PolicyDto();
        policyDto.setId(policy.getId());
        policyDto.setPolicyName(policy.getPolicyName());
        policyDto.setPolicyInsurerName(policy.getPolicyInsurerName());
        policyDto.setPolicyNumber(policy.getPolicyNumber());
        policyDto.setPolicyType(policy.getPolicyType());
        policyDto.setPolicyStatus(policy.getPolicyStatus());
        policyDto.setPolicyTerm(policy.getPolicyTerm());
        policyDto.setPolicyStartDate(policy.getPolicyStartDate());
        policyDto.setPolicyEndDate(policy.getPolicyEndDate());

        // premium info
        policyDto.setPremiumAmount(policy.getPremiumAmount());
        policyDto.setPremiumFrequency(policy.getPremiumFrequency());
        policyDto.setPremiumPaymentTerms(policy.getPremiumPayingTerm());
        policyDto.setPremiumDueDate(policy.getPremiumDueDate());
        policyDto.setFundValue(policy.getFundValue());
        policyDto.setBaseSumAssured(policy.getBaseSumAssured());

        // life assured
        policyDto.setInsuredPersonName(policy.getInsuredPersonName());
        policyDto.setPhoneNumber(policy.getPhoneNumber());
        policyDto.setEmailAddress(policy.getEmailAddress());
        policyDto.setInsuredPersonDateOfBirth(policy.getInsuredPersonDateOfBirth());
        policyDto.setRelationshipToTheInsured(policy.getRelationshipToTheInsured());

        return policyDto;
    }
}
