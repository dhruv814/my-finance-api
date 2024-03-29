package com.finance.policymanagement.repository;

import com.finance.policymanagement.entity.Policy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository
        extends JpaRepository<Policy, Long> {

    @Query("FROM Policy po WHERE po.createdBy = :createdBy")
    List<Policy> findByCreatorId(Long createdBy);
    
    @Query("FROM Policy po WHERE po.policyHolder.id = :policyHolderId")
    List<Policy> findByPolicyHolderId(Long policyHolderId);
    
    @Query("FROM Policy po WHERE po.lifeInsured.id = :lifeInsuredId")
    List<Policy> findByLifeInsuredId(Long lifeInsuredId);
}
