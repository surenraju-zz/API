package com.containersolutions.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.containersolutions.api.model.Person;
import com.containersolutions.api.model.PersonDTO;
import com.containersolutions.api.service.PersonService;

@RestController
public class PersonController {

	/* Person service */
	@Autowired
	private PersonService personService;

	/*
	 * List people
	 * 
	 * @return List of person
	 */
	@RequestMapping(path = "/people", method = RequestMethod.GET)
	public Iterable<Person> getPeople() {
		return personService.getPeople();
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
	@RequestMapping(path = "/people", method = RequestMethod.POST)
	public Person createPerson(@Valid @RequestBody PersonDTO person) {
		return personService.createPerson(person);
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
	@RequestMapping(path = "/people/{uuid}", method = RequestMethod.GET)
	public Person getPersonByUUID(@PathVariable(value = "uuid") String uuid) {
		return personService.getPersonByUUID(uuid);
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
	@RequestMapping(path = "/people/{uuid}", method = RequestMethod.PUT)
	public Person updatePerson(@PathVariable String uuid, @Valid @RequestBody PersonDTO inputPerson) {
		return personService.updatePerson(uuid, inputPerson);
	}

	/*
	 * Delete a {@code Person} by uuid
	 * 
	 * @param uuid of the {@code Person} to be deleted
	 * 
	 * @return Ok if delete successfully completes
	 * 
	 * @throws PersonNotFoundException if the uuid does not exists
	 */
	@RequestMapping(path = "/people/{uuid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePerson(@PathVariable String uuid) {
		personService.deletePerson(uuid);
		return ResponseEntity.ok().build();
	}
}