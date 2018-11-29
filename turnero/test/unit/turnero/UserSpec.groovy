package turnero

import grails.test.mixin.TestFor
import spock.lang.Specification
import utils.UserType

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test toMap()"() {
        given:
        User newUser =  new User()
        newUser.fistName = "Juan"
        newUser.lastName = "Perez"
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        when:
        def mapUser =  newUser.toMap()
        then:
        mapUser.fist_name == "Juan"
        mapUser.last_name == "Perez"
        mapUser.email == "jperez@gmail.com"
        mapUser.password == "password"
        mapUser.type == "PATIENT"
    }
}
