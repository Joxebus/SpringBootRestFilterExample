package com.nearsoft.challenge

import com.nearsoft.challenge.config.Bootstrap
import com.nearsoft.challenge.service.PersonService
import com.nearsoft.challenge.service.UserService
import spock.lang.Specification

class BootstrapSpec extends Specification {

    PersonService personService
    UserService userService
    Bootstrap bootstrap

    def setup(){
        personService = Mock()
        userService = Mock()
        bootstrap = new Bootstrap(personService, userService)
    }

    def "Test init method"(){
        when:
        bootstrap.init()

        then:
        1 * personService.count()
        5 * personService.create(_)
        1 * userService.count()
        2 * userService.create(_)
    }

}
