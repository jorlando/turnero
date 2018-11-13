package turnero

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import utils.UserType

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock(User)
class UserServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test findUser when exists"() {
        given:
        User newUser =  new User()
        newUser.fistName = "Juan"
        newUser.lastName = "Perez"
        newUser.alias = "jperez"
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        newUser.save(flush:true, failOnError: true)
        when:
        User userFound = service.findUser("jperez")
        then:
        userFound == newUser
        userFound.fistName == "Juan"
    }

    void "test findUser when not exists"() {
        when:
        User userFound = service.findUser("notFound")
        then:
        !userFound
    }

    void "test create patient"() {
        given:
        def alias = "alias"
        def name = "name"
        def lastName = "lastname"
        def email = "email"
        def password = "pwd"
        when:
        User newUser = service.createUser(alias, name, lastName, email, password)
        then:
        newUser.alias == alias
        newUser.fistName == name
        newUser.lastName == lastName
        newUser.email == email
        newUser.password == password
        newUser.type == UserType.PATIENT
    }

    void "test create doctor"() {
        given:
        def alias = "alias"
        def name = "name"
        def lastName = "lastname"
        def email = "email"
        def password = "pwd"
        when:
        User newUser = service.createUser(alias, name, lastName, email, password, "doctor")
        then:
        newUser.alias == alias
        newUser.fistName == name
        newUser.lastName == lastName
        newUser.email == email
        newUser.password == password
        newUser.type == UserType.DOCTOR
    }

    void "test login when user exists, correct password"() {
        given:
        User newUser =  new User()
        newUser.fistName = "Juan"
        newUser.lastName = "Perez"
        newUser.alias = "jperez"
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        newUser.save(flush:true, failOnError: true)
        when:
        boolean login = service.login("jperez", "password")
        then:
        login
    }

    void "test login when user exists, incorrect password"() {
        given:
        User newUser =  new User()
        newUser.fistName = "Juan"
        newUser.lastName = "Perez"
        newUser.alias = "jperez"
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        newUser.save(flush:true, failOnError: true)
        when:
        boolean login = service.login("jperez", "pss")
        then:
        !login
    }

    void "test login when user NOT exists"() {
        given:
        when:
        boolean login = service.login("jperez", "pss")
        then:
        !login
    }
}
