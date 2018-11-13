package turnero

import exceptions.BadRequestsException
import grails.converters.JSON

class UserController {

    UserService userService

    def login(){
        if (!params?.alias || !params?.password){
            throw new BadRequestsException("Fields are required: alias, password")
        }
        boolean loginSuccess = userService.login(params?.alias, params?.password)
        if(!loginSuccess){
            throw new BadRequestsException("login failure")
        }
        response.status = 200
        def bodyResponse = [login:"success"]
        render bodyResponse as JSON
    }

    def create(){
        if (!params?.alias || !params?.name || !params?.lastName || !params?.email || !params?.password){
            throw new BadRequestsException("Fields are required: alias, name, lastName, email, password")
        }else{
            User user = userService.createUser(params?.alias, params?.name, params?.lastName, params?.email, params?.password, params?.type)
            render user.toMap() as JSON
        }
    }
}
