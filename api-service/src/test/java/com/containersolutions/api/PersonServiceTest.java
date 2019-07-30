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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.containersolutions.api.exception.PersonNotFoundException;
import com.containersolutions.api.model.Person;
import com.containersolutions.api.model.PersonDTO;
import com.containersolutions.api.repository.PersonRepository;
import com.containersolutions.api.service.PersonService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceTest {
	@Autowired
	private PersonService personService;
	@MockBean
	private PersonRepository personRepository;
	private String uuid = UUID.randomUUID().toString();
	private String unknownUUID = UUID.randomUUID().toString();

	@TestConfiguration
	static class PeronServiceTestContextConfiguration {
		@Bean
		public PersonService peronService() {
			return new PersonService();
		}
	}

	@Before
	public void setUp() {
		// Mock for getPeopleByUUID
		Person alex = new Person();
		alex.setName("Alex");
		alex.setUuid(uuid);
		when(personRepository.findById(uuid)).thenReturn(Optional.of(alex));

		// Mock for getPeople
		List<Person> people = new ArrayList<>();
		people.add(alex);
		when(personRepository.findAll()).thenReturn(people);

		// Mock for updates
		when(personRepository.save(any(Person.class))).thenReturn(alex);
	}

	@Test
	public void createPersonReturnsCreatedObject() {
		PersonDTO alex = new PersonDTO();
		alex.setName("Alex");
		Person alexInDB = personService.createPerson(alex);
		Assert.assertEquals(alex.getName(), alexInDB.getName());
		Assert.assertNotNull(alexInDB.getUuid());
	}

	@Test
	public void updatePersonReturnsUpdatedPerson() {
		PersonDTO alex = new PersonDTO();
		alex.setName("Suren");
		Person alexInDB = personService.updatePerson(uuid, alex);
		Assert.assertEquals(alex.getName(), alexInDB.getName());
	}

	@Test
	public void updatePersonThrowsExceptionForInvalidUUID() {
		when(personRepository.findById(unknownUUID)).thenThrow(PersonNotFoundException.class);
		PersonDTO alex = new PersonDTO();
		alex.setName("Suren");
		Person alexInDB = personService.updatePerson(uuid, alex);
		Assert.assertEquals(alex.getName(), alexInDB.getName());
	}

	@Test
	public void getPeopleReturnOnePerson() {
		List<Person> people = personService.getPeople();
		Assert.assertEquals(1, people.size());
	}

	@Test
	public void getPersonByUUIDReturnsValidPersonForValidUUID() {
		Person found = personService.getPersonByUUID(uuid);
		Assert.assertEquals("Alex", found.getName());
	}

	@Test(expected = PersonNotFoundException.class)
	public void getPersonByUUIDThrowsExceptionForInvalidUUID() {
		when(personRepository.findById(unknownUUID)).thenThrow(PersonNotFoundException.class);
		personService.getPersonByUUID(unknownUUID);
	}

	@Test
	public void deletePersonRetunsFineForValidUUID() {
		personService.deletePerson(uuid);
	}

	@Test(expected = PersonNotFoundException.class)
	public void deletePersonThrowsExceptionForInvalidUUID() {
		when(personRepository.findById(unknownUUID)).thenThrow(PersonNotFoundException.class);
		personService.deletePerson(unknownUUID);
	}

}