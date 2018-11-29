package turnero

import utils.*

class Jornada {
    Dia dia
    int hora_desde
    int hora_hasta


    static constraints = {
    }

    def toMap(){
        [
                dia: dia.toString(),
         hora_desde:hora_desde,
         hora_hasta: hora_hasta
        ]
    }


}
