package com.finance.common.service;

import com.finance.common.dto.PersonDto;
import com.finance.common.entity.Person;
import com.finance.common.exception.ResourceNotFoundException;
import com.finance.common.repository.PersonRepository;
import com.finance.common.service.infc.PersonService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonDto createPerson(PersonDto person) {
        if (LocalDate.now().isBefore(person.getDateOfBirth())) {
            throw new ResourceNotFoundException("Date is not valid plese provide past date...");
        }
        Person created = personRepository.save(modelMapper.map(person, Person.class));
        return modelMapper.map(created, PersonDto.class);
    }

    @Override
    public PersonDto updatePerson(Long personId, PersonDto person) {

        personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person ", "personId", "" + personId));
        Person updated = personRepository.save(modelMapper.map(person, Person.class));
        return modelMapper.map(updated, PersonDto.class);
    }

    @Override
    public void deletePerson(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person ", "personId", "" + id));
        personRepository.delete(person);
    }

    @Override
    public PersonDto getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person ", "personId", "" + id));
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> getAllPerson() {
        List<Person> persons = personRepository.findAll();
        List<PersonDto> personsDto = persons.stream().map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());
        return personsDto;
    }

}
