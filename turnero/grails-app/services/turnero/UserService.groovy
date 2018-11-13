package turnero

import grails.transaction.Transactional
import utils.UserType

@Transactional
class UserService {

    def createUser(alias, name, lastName, email, password, String type= "PATIENT") {
        UserType userType = UserType.valueOf(type.toUpperCase().toString())
        User newUser = new User()
        newUser.fistName = name
        newUser.lastName = lastName
        newUser.email = email
        newUser.password = password
        newUser.alias = alias
        newUser.type = userType
        newUser.save(flush:true, failOnError:true)
        newUser
    }

    def findUser(alias){
        return User.findByAlias(alias)
    }

    boolean login(alias, pwd){
        User user = findUser(alias)
        return (user && user?.password == pwd)
    }
}
