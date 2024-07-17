package com.finance.policymanagement.controller;

import com.finance.dto.ClaimDto;
import com.finance.policymanagement.entity.Claim;
import com.finance.policymanagement.service.infc.ClaimService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @PostMapping("/claim/{id}")
    public ResponseEntity<Claim> createClaim(@PathVariable("id") String id, @Valid @RequestBody ClaimDto claim) {
        return new ResponseEntity<>(claimService.createNewClaim(id, claim), HttpStatus.CREATED);
    }

    @PutMapping("/claim/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable("id") Long id, @Valid @RequestBody ClaimDto claim) {
        return new ResponseEntity<>(claimService.updateClaim(id, claim), HttpStatus.OK);
    }

    @GetMapping("/claim/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(claimService.getClaimById(id), HttpStatus.OK);
    }

    @DeleteMapping("/claim/{id}")
    public ResponseEntity<Void> deleteClaimById(@PathVariable("id") Long id) {
        claimService.deleteClaim(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Claim>> getAllClaims() {
        return new ResponseEntity<>(claimService.getAllClaim(), HttpStatus.OK);
    }

}
