package turnero

import utils.UserType

class User {
    Long id
    String fistName
    String lastName
    String email
    String password
    UserType type
    String especialidad = ""
    List<Jornada> jornadas = []

    static hasMany = [jornadas: Jornada]


    static constraints = {
    }

    def toMap(){
        [
                id:id,
                fist_name: fistName,
                last_name:lastName,
                email:email,
                password:password,
                type:type.toString(),
                especialidad:especialidad.toString(),
                jornadas:jornadas?.collect{it.toMap()}
        ]
    }
}
