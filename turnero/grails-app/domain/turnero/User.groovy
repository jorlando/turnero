package turnero

import utils.UserType

class User {
    Long id
    String name
    String email
    String password
    UserType type
    String especialidad = ""
    String nacimiento = ""
    String direccion = ""
    String plan_medico = ""
    List<Jornada> jornadas = []

    static hasMany = [jornadas: Jornada]


    static constraints = {
    }

    def toMap(){

        [
                id:id,
                name: name,
                email:email,
                password:password,
                type:type.toString(),
                nacimiento: nacimiento,
                direccion: direccion,
                plan_medico: plan_medico,
                speciality:especialidad.toString(),
                image: null,
                jornadas:jornadas?.collect{it.toMap()}
        ]
    }
}
