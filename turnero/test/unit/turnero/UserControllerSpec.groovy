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
            return (args[0]=="jperez@gmail.com" && args[1]=="password")
        }

        userServiceMock.createUser(_, _, _, _, _) >> { args ->
            new User(fistName: args[0], lastName: args[1], email: args[2],password: args[3], type: UserType.PATIENT)
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
        ex.message == "Fields are required: email, password"
        ex.status == 400
    }

    void "test login with params, valid login"() {
        given:
        User newUser =  new User()
        newUser.fistName = "Juan"
        newUser.lastName = "Perez"
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        newUser.save(flush:true, failOnError: true)
        params.email = "jperez@gmail.com"
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
        newUser.email = "jperez@gmail.com"
        newUser.password = "password"
        newUser.type = UserType.PATIENT
        newUser.save(flush:true, failOnError: true)
        params.email = "jperez@gmail.com"
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
        params.email = "jperez@gmail.com"
        params.password = "invalidPwd"
        when:
        controller.create()
        then:
        BadRequestsException ex = thrown()
        ex.message == "Fields are required: name, lastName, email, password"
        ex.status == 400
    }

    void "test create with all params"() {
        given:
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
        response.json.email ==  "email"
        response.json.password == "invalidPwd"
        response.json.type == "PATIENT"
    }
}
