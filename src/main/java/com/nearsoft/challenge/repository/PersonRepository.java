package com.nearsoft.challenge.repository;

import com.nearsoft.challenge.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

}
