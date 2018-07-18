package com.nearsoft.challenge.repository;

import com.nearsoft.challenge.entity.UserAuthorization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorizationRepository extends CrudRepository<UserAuthorization, Integer> {

    UserAuthorization findFirstByUserNameAndToken(String username, String token);

    UserAuthorization findFirstByUserName(String username);
}
