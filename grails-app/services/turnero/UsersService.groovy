package turnero

import grails.gorm.transactions.Transactional
import utils.UserType

@Transactional
class UsersService {

    def createUser(alias, name, lastName, email, password) {
    	Users newUser = new Users()
    	newUser.fistName = name
    	newUser.lastName = lastName
    	newUser.email = email
    	newUser.password = password
    	newUser.alias = alias
    	newUser.type = UserType.PATIENT
    	newUser.save(flush:true, failOnError:true)
    }

    def findUser(alias){
    	return Users.findByAlias(alias)
    }

    boolean login(alias, pwd){
    	Users user = findUser(alias)
    	return (user && user?.password == pwd)
    }
}
