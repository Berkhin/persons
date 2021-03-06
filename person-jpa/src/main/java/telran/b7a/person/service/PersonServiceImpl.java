package telran.b7a.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.b7a.person.dao.PersonRepository;
import telran.b7a.person.dto.AddressDto;
import telran.b7a.person.dto.AgeBetweenDto;
import telran.b7a.person.dto.PersonDto;
import telran.b7a.person.dto.exception.PersonNotFoundException;
import telran.b7a.person.model.Address;
import telran.b7a.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	PersonRepository personRepository;
	ModelMapper modelMapper;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
		this.personRepository = personRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public boolean addPerson(PersonDto personDto) {
		if(personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		personRepository.deleteById(id);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		person.setName(name);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersonAddress(Integer id, AddressDto addsressDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		Address newAddress = new Address(addsressDto.getCity(), addsressDto.getStreet(), addsressDto.getBuilding());
		person.setAddress(newAddress);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findPersonByName(String name) {
		return personRepository.findByNameIgnoreCase(name)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonBetweenAges( AgeBetweenDto ageBetweenDto) {
		LocalDate minLocalDateAge = LocalDate.now().minusYears(ageBetweenDto.getMaxAge());
		LocalDate maxLocalDateAge = LocalDate.now().minusYears(ageBetweenDto.getMinAge());
		return personRepository.findByBirthDateBetween(minLocalDateAge, maxLocalDateAge)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonByCity(String city) {		
		return personRepository.findByAddressCityIgnoreCase(city)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

}
