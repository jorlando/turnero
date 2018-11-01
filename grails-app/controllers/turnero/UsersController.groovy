package turnero

import grails.converters.JSON

class UsersController {

	UsersService usersService

    def index() {
    	usersService
     }
     def login(){
     	boolean loginSucess = usersService.login(params?.alias, params?.password)
     	render(view:"loginOk", model:[user:params?.alias, sucess: loginSucess])
     }

     def create(){
     	Users user = usersService.createUser(params?.alias, params?.name, params?.lastName, params?.email, params?.password)
     	render user.toMap() as JSON
     }
}
