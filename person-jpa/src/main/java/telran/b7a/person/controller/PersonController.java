package telran.b7a.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.person.dto.AddressDto;
import telran.b7a.person.dto.AgeBetweenDto;
import telran.b7a.person.dto.PersonDto;
import telran.b7a.person.service.PersonService;

@RequestMapping("/person")
@RestController
@Transactional
public class PersonController {
	PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/find/{id}")
	public PersonDto findPerson(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@DeleteMapping("/remove/{id}")
	public PersonDto removePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
		
	}
	@PutMapping("/update/{id}/user/{name}")
	public PersonDto updatePersonName(@PathVariable Integer id,@PathVariable String name) {
		return personService.updatePersonName(id, name);
		
	}
	@PutMapping("/update/{id}/")
	public PersonDto updatePersonAddress(@PathVariable Integer id,@RequestBody AddressDto addsressDto) {
		return personService.updatePersonAddress(id, addsressDto);
		
	}
	@GetMapping("/find/all/{name}")
	public Iterable<PersonDto> findPersonByName(@PathVariable String name){
		return personService.findPersonByName(name);
		
	}
	@GetMapping("/find/ages")
	public Iterable<PersonDto> findPersonBetweenAges(@RequestBody AgeBetweenDto ageBetweenDto){
		return personService.findPersonBetweenAges(ageBetweenDto);
		
	}
	@GetMapping("/find/city/{city}")
	public Iterable<PersonDto> findPersonByCity(@PathVariable String city){
		return personService.findPersonByCity(city);
		
	}
}
