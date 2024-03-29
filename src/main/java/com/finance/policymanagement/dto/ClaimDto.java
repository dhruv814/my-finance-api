package com.finance.dto;

import com.finance.policymanagement.dto.PolicyDto;
import com.finance.policymanagement.dto.ClaimStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimDto {

    private String id;

    @NotBlank(message = "Claim Number is required")
    private String claimNumber;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Claim Date is required")
    private LocalDate claimDate;

    @NotNull(message = "Claim Status is required")
    private ClaimStatus claimStatus;

    private PolicyDto policy;
}
