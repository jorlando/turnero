package turnero


class Turno {

    Long id
    User doctor
    User patient
    Date fecha

    static constraints = {
    }

    def toMap(){
        [id:id,
        doctor: doctor.toMap(),
         patient: patient.toMap(),
        date: fecha.toString()]
    }
}
