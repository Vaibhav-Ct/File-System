package com.spotify.application.example.multithreading.repository;

import com.spotify.application.example.multithreading.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
