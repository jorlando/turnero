package turnero

import exceptions.BadRequestsException
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class UserController {

    UserService userService

    def login(){
        JSONObject body = request.JSON
        if (!body?.email || !body?.password){
            throw new BadRequestsException("Fields are required: email, password")
        }
        def user = userService.login(body?.email, body?.password)
        if(!user){
            throw new BadRequestsException("login failure")
        }
        response.status = 200
        def bodyResponse = user.toMap()
        render bodyResponse as JSON
    }

    def create(){
        JSONObject body = request.JSON

        if (!body?.nombre || !body?.email || !body?.password || !body.nacimiento || !body.direccion || !body.plan_medico){
            throw new BadRequestsException("Fields are required: nombre, email, password, nacimiento, direccion, plan_medico")
        }else{
            User user = userService.createUser(body?.nombre, body?.email, body?.password, body.nacimiento, body.direccion, body.plan_medico, body?.type)
            render user.toMap() as JSON
        }
    }

    def findDoctors(){
        def doctors = userService.findDoctors()
        def resp = [doctors: doctors]
        render resp as JSON
    }

    def findTurno(){
        def turnos = userService.findTurnos(params.userId)
        render turnos as JSON
    }

    def createTurno(){
        JSONObject body = request.JSON
        def turno = userService.createTurno(body.patient_id, body.doctor_id, body.date, body.hour, body.comment)
        render turno.toMap() as JSON
    }
    def cancelTurno(){
        userService.cancelTurno(params.turnoId)
        def resp = [message:"turno cancelado"]
        render resp as JSON
    }

    def home(){
        def resp = [user: userService.findUserById(params.userId)?.toMap(),
                turnos:userService.findTurnos(params.userId)
        ]
        render resp as JSON


    }
}
