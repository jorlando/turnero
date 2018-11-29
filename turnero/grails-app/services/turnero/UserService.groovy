package turnero

import exceptions.BadRequestsException
import grails.transaction.Transactional
import utils.Dia
import utils.UserType

@Transactional
class UserService {

    def createUser(name, lastName, email, password, String type= "PATIENT") {
        UserType userType = UserType.valueOf(type.toUpperCase().toString())
        User newUser = new User()
        newUser.fistName = name
        newUser.lastName = lastName
        newUser.email = email
        newUser.password = password
        newUser.type = userType
        newUser.save(flush:true, failOnError:true)
        newUser
    }

    def findUser(String email){
        return User.findByEmail(email.toString())
    }
    def findUserById(id){
        return User.findById(id)
    }

    def login(email, pwd){
        User user = findUser(email.toString())
        if (user && user?.password == pwd){
            return user
        }
        return null
    }

    def findDoctors(){
        def list = User.findAllByType(UserType.DOCTOR)
        return list.collect{it.toMap()}
    }

    def findTurnos(userId){
        User user=this.findUserById(userId)
        println user
        List<Turno> turnos = []
        turnos << Turno.findAllByPatientAndFechaGreaterThan(user, new Date())
        turnos << Turno.findAllByDoctorAndFechaGreaterThan(user, new Date())
        println turnos
        turnos = turnos.flatten()
        turnos = turnos.findAll()
        turnos = turnos?.sort{it.fecha}
        turnos?.collect{it.toMap()}
    }

    def createTurno(patientId,doctorId,dateTurno){
        User patient = User.findById(patientId)
        User doctor = User.findByIdAndType(doctorId,UserType.DOCTOR)
        Date fechaTurno = parseDate(dateTurno)
        validateDateTurno(doctor, fechaTurno)
        Turno newTurno = new Turno(doctor:doctor,fecha: fechaTurno, patient:patient)
        newTurno.save(flush: true, failOnError: true)
        return newTurno
    }

    Date parseDate(date){
        new Date().parse("yyyy-MM-dd HH:mm", date)
    }

    void validateDateTurno(User doctor, Date fechaTurno){
        Dia dia = Dia.findInt(fechaTurno.getDay())
        def atiende = doctor.jornadas.find{Jornada jornada ->
            jornada.dia == dia && fechaTurno.hours >=jornada.hora_desde && fechaTurno.hours< jornada.hora_hasta
        }
        if (!atiende){
            throw new BadRequestsException("no atiende")
        }
    }

    def cancelTurno(turnoId){
        Turno turno = Turno.findById(turnoId)
        turno?.delete(flush: true, failOnError:true)
    }

}
