package com.nearsoft.challenge.service;

import com.nearsoft.challenge.entity.Person;
import com.nearsoft.challenge.repository.PersonRepository;
import com.nearsoft.challenge.utils.PersonValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person create(Person newPerson) {
        PersonValidator.validate(newPerson);
        Person person = personRepository.save(newPerson);
        logger.debug("Person created: {}", person);
        return person;
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public long count(){
        return personRepository.count();
    }

    public Person update(Person newPerson) {
        PersonValidator.validate(newPerson);
        if(newPerson.getId() < 1){
            throw new IllegalArgumentException("Can't update person with id ="+newPerson.getId());
        }
        Person person = personRepository.findOne(newPerson.getId());
        person.setName(newPerson.getName());
        person.setLastName(newPerson.getLastName());
        person.setAge(newPerson.getAge());
        person.setPhone(newPerson.getPhone());
        person = personRepository.save(person);
        logger.debug("Person updated: {}", person);
        return person;
    }

    public void delete(int id) {
        Person person = personRepository.findOne(id);
        if(person == null || person.getId() < 1){
            throw new IllegalArgumentException("Can't delete person with id ="+id);
        }
        logger.debug("Deleting person: {}", person);
        personRepository.delete(person);
    }

    public Person findById(int id) {
        Person person = personRepository.findOne(id);
        if(person == null){
            throw new IllegalArgumentException("There is no person with id ="+id);
        }
        logger.debug("Person found: {}", person);
        return person;
    }


}
