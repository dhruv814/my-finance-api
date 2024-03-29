package com.finance.policymanagement.service;

import com.finance.common.exception.ResourceNotFoundException;
import com.finance.policymanagement.dto.PolicyDto;
import com.finance.policymanagement.entity.Policy;
import com.finance.policymanagement.repository.PolicyRepository;
import com.finance.policymanagement.service.infc.PolicyService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PolicyDto createPolicy(PolicyDto policy) {
        Policy policyInfo = modelMapper.map(policy, Policy.class);
        Policy savedPolicy = policyRepository.save(policyInfo);
        return modelMapper.map(savedPolicy, PolicyDto.class);
    }

    @Override
    public PolicyDto updatePolicy(Long id, PolicyDto policy) {
        policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance Policy", "policy ", "" + id));
        Policy updatedInfo = modelMapper.map(policy, Policy.class);
        updatedInfo.setId(id);
        updatedInfo = policyRepository.save(updatedInfo);
        return modelMapper.map(updatedInfo, PolicyDto.class);
    }

    @Override
    public void deletePolicy(Long id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance Policy", "policy ", "" + id));
        policyRepository.delete(policy);
    }

    @Transactional
    @Override
    public PolicyDto getPolicyById(Long id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance Policy", "policy ", "" + id));
        return modelMapper.map(policy, PolicyDto.class);
    }

    @Override
    public List<PolicyDto> getAllPolicies() {
        List<Policy> policies = policyRepository.findAll();
        return policies.stream().map(policy
                -> modelMapper.map(policy, PolicyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyDto> getPoliciesByCreator(Long createdBy) {
        List<Policy> policies = policyRepository.findByCreatorId(createdBy);
        return policies.stream().map(policy
                -> modelMapper.map(policy, PolicyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyDto> getPoliciesByPolicyHolder(Long policyHolderId) {
        List<Policy> policies = policyRepository.findByPolicyHolderId(policyHolderId);
        return policies.stream().map(policy
                -> modelMapper.map(policy, PolicyDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PolicyDto> getPoliciesByLifeInsured(Long lifeInsuredId) {
        List<Policy> policies = policyRepository.findByLifeInsuredId(lifeInsuredId);
        return policies.stream().map(policy
                -> modelMapper.map(policy, PolicyDto.class))
                .collect(Collectors.toList());
    }

}
