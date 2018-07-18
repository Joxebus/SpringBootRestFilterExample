package com.nearsoft.challenge.repository;

import com.nearsoft.challenge.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findFirstByUsername(String username);
}
