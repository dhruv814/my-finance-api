package com.finance.policymanagement.service.infc;

import com.finance.policymanagement.dto.PolicyDto;
import java.util.List;

public interface PolicyService {

    PolicyDto createPolicy(PolicyDto policy);

    PolicyDto updatePolicy(Long id, PolicyDto policy);

    void deletePolicy(Long id);

    PolicyDto getPolicyById(Long id);

    List<PolicyDto> getAllPolicies();

    List<PolicyDto> getPoliciesByCreator(Long createdBy);

    List<PolicyDto> getPoliciesByPolicyHolder(Long policyHolderId);

    List<PolicyDto> getPoliciesByLifeInsured(Long lifeInsuredId);

}
