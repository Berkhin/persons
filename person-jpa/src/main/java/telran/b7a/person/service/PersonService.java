package telran.b7a.person.service;

import telran.b7a.person.dto.AddressDto;
import telran.b7a.person.dto.AgeBetweenDto;
import telran.b7a.person.dto.PersonDto;

public interface PersonService {
	
	boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);
	
	PersonDto removePerson(Integer id);
	
	PersonDto updatePersonName(Integer id, String name);
	
	PersonDto updatePersonAddress(Integer id, AddressDto adsressDto);
	
	Iterable<PersonDto> findPersonByName(String name);
	
	Iterable<PersonDto> findPersonBetweenAges(AgeBetweenDto ageBetweenDto);
	
	Iterable<PersonDto> findPersonByCity(String city);;	
}
