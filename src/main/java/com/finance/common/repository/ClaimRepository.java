package com.finance.common.repository;

import com.finance.policymanagement.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository
        extends JpaRepository<Claim, Long> {

}
