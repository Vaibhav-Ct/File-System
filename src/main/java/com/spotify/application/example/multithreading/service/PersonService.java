package com.spotify.application.example.multithreading.service;

import com.spotify.application.example.multithreading.dto.PersonDTO;
import com.spotify.application.example.multithreading.model.Person;
import com.spotify.application.example.multithreading.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> readAndParseCSV(String filePath) {
        List<PersonDTO> persons = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int age = Integer.parseInt(values[1]);
                String email = values[2];

                persons.add(new PersonDTO(name, age, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return persons;
    }


    public List<Person> savePersons(List<PersonDTO> personDTOList) {
        List<Person> persons = new ArrayList<>();
        for (PersonDTO dto : personDTOList) {
            Person person = new Person(dto.getName(), dto.getAge(), dto.getEmail());
            persons.add(personRepository.save(person));
        }
        return persons;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}
