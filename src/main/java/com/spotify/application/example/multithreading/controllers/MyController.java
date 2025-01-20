
package com.spotify.application.example.multithreading.controllers;

import com.spotify.application.example.multithreading.dto.PersonDTO;
import com.spotify.application.example.multithreading.model.Person;
import com.spotify.application.example.multithreading.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    private final PersonService personService;

    @Autowired
    public MyController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/parse-csv")
    public List<PersonDTO> parseCSV() {
        String csvFilePath = "src/main/resources/data.csv";
        List<PersonDTO> personDTOList = personService.readAndParseCSV(csvFilePath);
        return personDTOList;
    }
}
