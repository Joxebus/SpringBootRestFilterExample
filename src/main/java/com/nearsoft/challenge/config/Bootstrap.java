package com.nearsoft.challenge.config;

import com.nearsoft.challenge.entity.Person;
import com.nearsoft.challenge.entity.User;
import com.nearsoft.challenge.service.PersonService;
import com.nearsoft.challenge.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;

@Component
public class Bootstrap {

    Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    @Value("${local.server.address:http://localhost}")
    private String address;
    @Value("${local.server.port:8080}")
    private String port;
    @Value("${spring.h2.console.path:/h2}")
    private String h2Path;

    private PersonService personService;
    private UserService userService;

    public Bootstrap(PersonService personService, UserService userService) {
        this.personService = personService;
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        logger.info("Loading initial information");

        if(personService.count() == 0) {
            loadPersons();
        }

        if(userService.count() == 0) {
            loadUsers();
        }

        logger.info("Finish to load initial information");
        logger.info("Application running at: {}:{}", address, port);
        logger.info("H2 database manager at: {}:{}{}", address, port, h2Path);
    }

    private void loadPersons(){
        // Requires extra dependency see: build.gradle
        try (JsonReader jsonReader =
                Json.createReader(getClass().getClassLoader().getResource("persons.json").openStream())) {
            jsonReader.readArray().forEach(jsonValue -> {
                JsonObject p = jsonValue.asJsonObject();
                Person person = new Person();
                person.setName(p.getString("name"));
                person.setLastName(p.getString("lastName"));
                person.setAge(p.getInt("age"));
                person.setPhone(p.getString("phone"));
                personService.create(person);
                logger.debug("Loading person: {}", person);
            });
        } catch (IOException e) {
            logger.error("Error trying to load persons.json", e);
        }
    }


    private void loadUsers(){
        // Requires extra dependency see: build.gradle
        try (JsonReader jsonReader =
                Json.createReader(getClass().getClassLoader().getResource("users.json").openStream())) {
            jsonReader.readArray().forEach(jsonValue -> {
                JsonObject p = jsonValue.asJsonObject();
                User user = new User();
                user.setUsername(p.getString("username"));
                user.setPassword(p.getString("password"));
                userService.create(user);
                logger.debug("Loading user: {}", user);
            });
        } catch (IOException e) {
            logger.error("Error trying to load users.json", e);
        }
    }

}
