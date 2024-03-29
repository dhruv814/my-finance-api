package com.finance.common.controller;

import com.finance.common.dto.PersonDto;
import com.finance.common.service.infc.PersonService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto person) {
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Long id, @Valid @RequestBody PersonDto person) {
        return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.OK);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonDto>> getAllPerson() {
        return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.OK);
    }

}
