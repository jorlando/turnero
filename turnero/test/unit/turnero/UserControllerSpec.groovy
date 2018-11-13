package turnero

import exceptions.BadRequestsException
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import utils.UserType

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@Mock(User)
@TestFor(UserController)
class UserControllerSpec extends Specification {

    def setup() {
        UserService userServiceMock = Mock(UserService)
        userServiceMock.login(_,_)  >> { args ->
            return (args[0]=="jperez" && args[1]=="password")
        }

        userServiceMock.createUser(_, _, _, _, _, _) >> { args ->
            new User(alias: args[0], fistName: args[1], lastName: args[2], email: args[3],password: args[4], type: UserType.PATIENT)
        }
        controller.userService = userServiceMock
    }

    def cleanup() {
    }

    void "test login without params"() {
        when:
        controller.login()
        then:
        BadRequestsException ex = thrown()
        ex.message == "Fields are required: alias, password"
        ex.status == 400
    }

    void "test login with params, valid login"() {
        given:
        User newUser =  new User()
        newUser.fistName = "Juan"
        newUser.lastName = "Perez"
        newUser.alias = "jperez"
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        newUser.save(flush:true, failOnError: true)
        params.alias = "jperez"
        params.password = "password"
        when:
        controller.login()
        then:
        response.json.login == "success"
        response.status == 200
    }

    void "test login with params, invalid pwd"() {
        given:
        User newUser =  new User()
        newUser.fistName = "Juan"
        newUser.lastName = "Perez"
        newUser.alias = "jperez"
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        newUser.save(flush:true, failOnError: true)
        params.alias = "jperez"
        params.password = "invalidPwd"
        when:
        controller.login()
        then:
        BadRequestsException ex = thrown()
        ex.message == "login failure"
        ex.status == 400
    }

    void "test create without all params"() {
        given:
        params.alias = "jperez"
        params.password = "invalidPwd"
        when:
        controller.create()
        then:
        BadRequestsException ex = thrown()
        ex.message == "Fields are required: alias, name, lastName, email, password"
        ex.status == 400
    }

    void "test create with all params"() {
        given:
        params.alias = "jperez"
        params.password = "invalidPwd"
        params.name = "name"
        params.email = "email"
        params.lastName = "lastName"
        params.type = "patient"
        when:
        controller.create()
        then:
        response.json.fist_name == "name"
        response.json.last_name == "lastName"
        response.json.alias == "jperez"
        response.json.email ==  "email"
        response.json.password == "invalidPwd"
        response.json.type == "PATIENT"
    }
}
