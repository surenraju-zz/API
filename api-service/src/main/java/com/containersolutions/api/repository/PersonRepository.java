package com.containersolutions.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.containersolutions.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, String> {
}
