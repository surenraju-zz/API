package com.containersolutions.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.containersolutions.api.exception.PersonNotFoundException;
import com.containersolutions.api.model.Person;
import com.containersolutions.api.model.PersonDTO;
import com.containersolutions.api.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	/*
	 * List people
	 * 
	 * @return List of person
	 */
	public List<Person> getPeople() {
		return personRepository.findAll();
	}

	/*
	 * Create a {@code Person}
	 * 
	 * @param person the {@code Person} to be created
	 * 
	 * @return created {@code Person}
	 * 
	 * @throws ParseException if the cron expression is not a valid quartz cron
	 */
	public Person createPerson(PersonDTO person) {
		Person personToCreate = new Person(person);
		personToCreate.setUuid(UUID.randomUUID().toString());
		return personRepository.save(personToCreate);
	}

	/*
	 * Get a {@code Person} by uuid
	 * 
	 * @param uuid of the {@code Person} to be fetched
	 * 
	 * @return created {@code Person}
	 * 
	 * @throws PersonNotFoundException if the uuid does not exists
	 */
	public Person getPersonByUUID(String uuid) {
		return personRepository.findById(uuid).orElseThrow(() -> new PersonNotFoundException());
	}

	/*
	 * Update a {@code Person}
	 * 
	 * @param uuid of the {@code Person} to be updated
	 * 
	 * @param inputPerson the {@code Person} to be updated
	 * 
	 * @return updated {@code Person}
	 * 
	 * @throws PersonNotFoundException if the uuid does not exists
	 */
	public Person updatePerson(String uuid, PersonDTO inputPerson) {
		Person personToUpdate = personRepository.findById(uuid).orElseThrow(() -> new PersonNotFoundException());
		personToUpdate.setAge(inputPerson.getAge());
		personToUpdate.setFare(inputPerson.getFare());
		personToUpdate.setName(inputPerson.getName());
		personToUpdate.setParentsOrChildrenAboard(inputPerson.getParentsOrChildrenAboard());
		personToUpdate.setPassengerClass(inputPerson.getPassengerClass());
		personToUpdate.setSex(inputPerson.getSex());
		personToUpdate.setSiblingsOrSpousesAboard(inputPerson.getSiblingsOrSpousesAboard());
		personToUpdate.setSurvived(inputPerson.isSurvived());
		return personRepository.save(personToUpdate);
	}

	/*
	 * Delete a {@code Person} by uuid
	 * 
	 * @param uuid of the {@code Person} to be deleted
	 * 
	 * @throws PersonNotFoundException if the uuid does not exists
	 */
	public void deletePerson(String uuid) {
		Person personInDB = personRepository.findById(uuid).orElseThrow(() -> new PersonNotFoundException());
		personRepository.delete(personInDB);
	}
}
