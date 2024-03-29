package com.finance.service.infc;

import com.finance.dto.ClaimDto;
import com.finance.policymanagement.entity.Claim;
import java.util.List;

public interface ClaimService {

    /**
     *
     * @param policyId
     * @param claim : take all about claim detail
     * @return : return a saved claim in database
     */
    Claim createNewClaim(Long policyId, ClaimDto claim);

    /**
     *
     * @param claimId: take claim id by user and check this id exist in database
     * or not if not exist in database then throw a particular exception if exit
     * in database then fetch and store a particular variable
     * @return : return a this store claim detail in this variable.
     */
    Claim getClaimById(Long claimId);

    /**
     *
     * @param claim : take all about claim detail
     * @param claimId : take claim id by user and check this id exist in
     * database or not if not exist in database then throw a particular
     * exception if exit in database then fetch and store a particular variable
     *
     * after all operation swap all claim parameter data in fetch claim data.
     * after save in database
     * @return :return this save info in database
     */
    Claim updateClaim(Long claimId, ClaimDto claim);

    /**
     *
     * @param claimId: take claim id by user and check this id exist in database
     * or not if not exist in database then throw a particular exception if exit
     * in database then fetch and store a particular variable after this delete
     * all info in database
     */
    void deleteClaim(Long claimId);

    /**
     *
     * @return : this return all about claims data in who's store in database..
     */
    List<Claim> getAllClaim();

}
