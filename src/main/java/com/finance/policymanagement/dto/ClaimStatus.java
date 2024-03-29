package com.finance.policymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClaimStatus {
    SUBMITTED("Submitted"),
    IN_PROGRESS("In Progress"),
    APPROVED("Approved"),
    DENIED("Denied");

    private final String displayName;

}
