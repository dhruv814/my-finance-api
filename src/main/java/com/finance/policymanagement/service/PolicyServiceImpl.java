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
@Transactional
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final PolicyMapper policyMapper = new PolicyMapper();

    @Override
    public PolicyDto createPolicy(PolicyDto policyDto) {
        //Policy policyInfo = modelMapper.map(policyDto, Policy.class);
        Policy policyInfo = policyMapper.toEntity(policyDto);
        Policy savedPolicy = policyRepository.save(policyInfo);
        return policyMapper.toDto(savedPolicy);
        //return modelMapper.map(savedPolicy, PolicyDto.class);
    }

    @Override
    public PolicyDto updatePolicy(String id, PolicyDto policy) {
        policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance Policy", "policy ", "" + id));
        Policy updatedInfo = modelMapper.map(policy, Policy.class);
        updatedInfo.setId(id);
        updatedInfo = policyRepository.save(updatedInfo);
        return modelMapper.map(updatedInfo, PolicyDto.class);
    }

    @Override
    public void deletePolicy(String id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance Policy", "policy ", "" + id));
        policyRepository.delete(policy);
    }

    @Override
    public PolicyDto getPolicyById(String id) {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance Policy", "policy ", "" + id));
        return policyMapper.toDto(policy);
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

}
