package com.finance.policymanagement.service;

import com.finance.dto.ClaimDto;
import com.finance.policymanagement.dto.PolicyDto;
import com.finance.policymanagement.entity.Claim;
import com.finance.common.exception.ResourceNotFoundException;
import com.finance.common.repository.ClaimRepository;
import com.finance.service.infc.ClaimService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.policymanagement.service.infc.PolicyService;

@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Claim createNewClaim(Long policyId, ClaimDto claim) {
        PolicyDto policy = policyService.getPolicyById(policyId);
//		System.out.println(policy);
//		Set<ClaimDto> claims = policy.getClaims();
//		claims.add(claim);
//		policy.setClaims(claims);
        claim.setPolicy(policy);

//		insurancePolicyRepository.save(modelMapper.map(policy, Policy.class));
        Claim claimd = modelMapper.map(claim, Claim.class);
        return claimRepository.save(claimd);
    }

    @Override
    public Claim getClaimById(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim ", "Claim id", "" + claimId));
    }

    @Override
    public Claim updateClaim(Long claimId, ClaimDto claim) {
        claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim ", "Claim id", "" + claimId));
        Claim newClaim = modelMapper.map(claim, Claim.class);
        return claimRepository.save(newClaim);
    }

    @Override
    public void deleteClaim(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim ", "Claim id", "" + claimId));
        claimRepository.delete(claim);
    }

    @Override
    public List<Claim> getAllClaim() {
        return claimRepository.findAll();
    }

}
