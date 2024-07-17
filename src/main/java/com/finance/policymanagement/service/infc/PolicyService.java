package com.finance.policymanagement.service.infc;

import com.finance.policymanagement.dto.PolicyDto;
import java.util.List;

public interface PolicyService {

    PolicyDto createPolicy(PolicyDto policy);

    PolicyDto updatePolicy(String id, PolicyDto policy);

    void deletePolicy(String id);

    PolicyDto getPolicyById(String id);

    List<PolicyDto> getAllPolicies();

    List<PolicyDto> getPoliciesByCreator(Long createdBy);

}
