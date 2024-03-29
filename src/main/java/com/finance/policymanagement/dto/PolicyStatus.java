package com.finance.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author dhruvkumar
 */
@Getter
@AllArgsConstructor
public enum PolicyStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    CANCELED("Canceled"),
    LAPSED("Lapsed");

    private final String status;

}
