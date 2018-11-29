package turnero


class Turno {

    Long id
    User doctor
    User patient
    Date fecha
    String comment

    static constraints = {
    }

    def toMap(){
        [
                id:id,
                doctor: doctor.toMap(),
                patient: patient.toMap(),
                date: this.getFechaString(),
                hour: this.getHourString(),
                comment: comment.toString()
        ]
    }

    def getFechaString(){
        "${fecha.getYear()+1900}-${fecha.getMonth()+1}-${fecha.getDate()}"
    }
    def getHourString(){
        "${fecha.getHours()}:${fecha.getMinutes()}"
    }
}
