package com.finance.policymanagement.entity;

import com.finance.common.entity.Person;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author dhruvkumar
 */
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@ToString
//@Entity
public class PolicyHolder extends Person {
        
//    private String relationShipToInsured;
//
//    @OneToMany(mappedBy = "policyHolder", cascade = CascadeType.ALL)
//    private List<Policy> policies;
}
