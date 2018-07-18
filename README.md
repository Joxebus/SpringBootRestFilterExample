# Spring Boot Filter Example without Spring Security


This repo has examples of how to add a filter to an application without use Spring Security,
and it's only with educational purposes

If you are using IntelliJIDEa open the project and select the options:

- Use auto-import
- Create directories for empty content roots automatically
- Create separate module per source set

and finally

- Use default gradle wrapper

## URIs

By default you will have available the next REST URIs
all consumes and produces "application/json" 

### Non secured
```
/
/login
/logout
```

### Secured

The secured require to login first and then send 
the username and token in the headers

```
/people
/users
```

## Tests

The tests for this project are written in Groovy with Spock Framework

## Structure of the project


```
├── README.md
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── nearsoft
    │   │           └── challenge
    │   │               ├── App.java
    │   │               ├── config
    │   │               │   ├── Bootstrap.java
    │   │               │   └── SecurityConfiguration.java
    │   │               ├── controller
    │   │               │   ├── PersonController.java
    │   │               │   ├── SecurityController.java
    │   │               │   └── UserController.java
    │   │               ├── entity
    │   │               │   ├── Person.java
    │   │               │   ├── User.java
    │   │               │   └── UserAuthorization.java
    │   │               ├── filter
    │   │               │   └── SecurityFilter.java
    │   │               ├── repository
    │   │               │   ├── PersonRepository.java
    │   │               │   ├── UserAuthorizationRepository.java
    │   │               │   └── UserRepository.java
    │   │               ├── service
    │   │               │   ├── PersonService.java
    │   │               │   ├── UserAuthorizationService.java
    │   │               │   └── UserService.java
    │   │               └── utils
    │   │                   └── PersonValidator.java
    │   └── resources
    │       ├── application.yml
    │       ├── banner.txt
    │       ├── log-config.xml
    │       ├── persons.json
    │       └── users.json
    └── test
        ├── groovy
        │   └── com
        │       └── nearsoft
        │           └── challenge
        │               ├── BootstrapSpec.groovy
        │               ├── PersonControllerRestSpec.groovy
        │               ├── PersonControllerSpec.groovy
        │               └── PersonServiceSpec.groovy
        └── resources
            └── application.yml
```



## Requirements

- Java 8
- Gradle 3.3
- Postman

## Run tests

For MacOS and Linux

`` ./gradlew test ``  

or for Windows

`` gradlew.bat test``
