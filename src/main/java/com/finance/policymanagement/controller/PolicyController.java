package com.finance.policymanagement.controller;

import com.finance.policymanagement.dto.PolicyDto;
import com.finance.policymanagement.service.infc.PolicyService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/policy")
    public ResponseEntity<PolicyDto> createPolicy(
            @Valid @RequestBody PolicyDto policy) {
        return new ResponseEntity<>(policyService.createPolicy(policy),
                HttpStatus.CREATED);
    }

    @PutMapping("/policy/{id}")
    public ResponseEntity<PolicyDto> updatePolicy(
            @PathVariable("id") String id,
            @Valid @RequestBody PolicyDto policy) {
        return new ResponseEntity<>(policyService.updatePolicy(id, policy),
                HttpStatus.OK);
    }

    @DeleteMapping("/policy/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable("id") String id) {
        policyService.deletePolicy(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/policy/{id}")
    public ResponseEntity<PolicyDto> getPolicyById(@PathVariable("id") String id) {
        return new ResponseEntity<>(policyService.getPolicyById(id),
                HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PolicyDto>> searchPolicy(
            @RequestParam(name = "policyHolderId", required = false) Long policyHolderId,
            @RequestParam(name = "lifeInsuredId", required = false) Long lifeInsuredId,
            @RequestParam(name = "createdBy", required = false) Long createdBy) {

        if (createdBy != null) {
            return new ResponseEntity<>(policyService.getPoliciesByCreator(createdBy),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(policyService.getAllPolicies(),
                    HttpStatus.OK);
        }

    }

}
