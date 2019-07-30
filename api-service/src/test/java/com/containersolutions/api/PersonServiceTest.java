package com.containersolutions.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.containersolutions.api.exception.PersonNotFoundException;
import com.containersolutions.api.model.Person;
import com.containersolutions.api.model.PersonDTO;
import com.containersolutions.api.repository.PersonRepository;
import com.containersolutions.api.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	@InjectMocks
	private PersonService personService;
	@Mock
	private PersonRepository personRepository;
	private static String uuid = UUID.randomUUID().toString();
	private static String unknownUUID = UUID.randomUUID().toString();

	@TestConfiguration
	static class PeronServiceTestContextConfiguration {
		@Bean
		public PersonService peronService() {
			return new PersonService();
		}
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = PersonNotFoundException.class)
	public void updatePersonThrowsExceptionForInvalidUUID() {
		PersonDTO alex = new PersonDTO();
		alex.setName("Suren");
		Person alexInDB = personService.updatePerson(uuid, alex);
		Assert.assertEquals(alex.getName(), alexInDB.getName());
	}

	@Test
	public void getPeopleReturnOnePerson() {
		// Mock for getPeople
		Person alex = new Person();
		alex.setName("Alex");
		alex.setUuid(uuid);
		List<Person> mockPeople = new ArrayList<>();
		mockPeople.add(alex);
		when(personRepository.findAll()).thenReturn(mockPeople);

		// Test
		List<Person> people = personService.getPeople();
		Assert.assertEquals(1, people.size());
	}

	@Test
	public void getPersonByUUIDReturnsValidPersonForValidUUID() {
		// Mock
		Person alex = new Person();
		alex.setName("Alex");
		alex.setUuid(uuid);
		when(personRepository.findById(uuid)).thenReturn(Optional.of(alex));

		// Test
		Person found = personService.getPersonByUUID(uuid);
		Assert.assertEquals("Alex", found.getName());
	}

	@Test(expected = PersonNotFoundException.class)
	public void getPersonByUUIDThrowsExceptionForInvalidUUID() {
		when(personRepository.findById(unknownUUID)).thenThrow(PersonNotFoundException.class);
		personService.getPersonByUUID(unknownUUID);
	}

	@Test(expected = PersonNotFoundException.class)
	public void deletePersonThrowsExceptionForInvalidUUID() {
		when(personRepository.findById(unknownUUID)).thenThrow(PersonNotFoundException.class);
		personService.deletePerson(unknownUUID);
	}

}