package com.finance.common.service.infc;

import com.finance.common.dto.PersonDto;
import java.util.List;

/**
 *
 * @author dhruvkumar
 */
public interface PersonService {

    PersonDto createPerson(PersonDto person);

    PersonDto updatePerson(Long id, PersonDto person);
    
    void deletePerson(Long id);
    
    PersonDto getPersonById(Long id);
    
    List<PersonDto> getAllPerson();
        
}
