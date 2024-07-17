package com.finance.policymanagement.repository;

import com.finance.policymanagement.entity.Policy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository
        extends JpaRepository<Policy, String> {

    @Query("FROM Policy po WHERE po.createdBy = :createdBy")
    List<Policy> findByCreatorId(Long createdBy);
    
}
