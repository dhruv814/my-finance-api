package com.finance.policymanagement.dto;

import com.finance.common.dto.PersonDto;
import com.finance.policymanagement.entity.Policy;
import java.util.List;

/**
 *
 * @author dhruvkumar
 */
public class LifeInsuredDto extends PersonDto {

    // one client can have many policies
    private List<Policy> policies;

}
