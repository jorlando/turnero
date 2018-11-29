package turnero

import exceptions.BadRequestsException
import grails.converters.JSON

class UserController {

    UserService userService

    def login(){
        if (!params?.email || !params?.password){
            throw new BadRequestsException("Fields are required: email, password")
        }
        def user = userService.login(params?.email, params?.password)
        if(!user){
            throw new BadRequestsException("login failure")
        }
        response.status = 200
        def bodyResponse = user.toMap()
        render bodyResponse as JSON
    }

    def create(){
        if (!params?.name || !params?.lastName || !params?.email || !params?.password){
            throw new BadRequestsException("Fields are required: name, lastName, email, password")
        }else{
            User user = userService.createUser(params?.name, params?.lastName, params?.email, params?.password, params?.type)
            render user.toMap() as JSON
        }
    }

    def findDoctors(){
        def doctors = userService.findDoctors()
        render doctors as JSON
    }

    def findTurno(){
        def turnos = userService.findTurnos(params.userId)
        render turnos as JSON
    }

    def createTurno(){
        def turno = userService.createTurno(params.paciente, params.doctor, params.fecha)
        render turno.toMap() as JSON
    }
    def cancelTurno(){
        userService.cancelTurno(params.turnoId)
        def resp = [message:"turno cancelado"]
        render resp as JSON
    }
}
