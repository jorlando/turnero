import grails.util.Environment
import  turnero.*;
import  utils.*;

class BootStrap {

    def init = { servletContext ->

		if (Environment.DEVELOPMENT == Environment.current){
			User doctor1 = new User(name:"Juan Perez", email:"juan.perez@me.com", password:"pw1", type: UserType.DOCTOR, especialidad:"Clinico", nacimiento:"1980-10-10", direccion:"Av Maipu 521")
			Jornada lunes1 = new Jornada(dia:Dia.LUNES, hora_desde:9, hora_hasta:18)
			Jornada martes1 = new Jornada(dia:Dia.MARTES, hora_desde:9, hora_hasta:18)
			Jornada miercoles1 = new Jornada(dia:Dia.MIERCOLES, hora_desde:9, hora_hasta:18)
			Jornada jueves1 = new Jornada(dia:Dia.JUEVES, hora_desde:9, hora_hasta:18)
			Jornada viernes1 = new Jornada(dia:Dia.VIERNES, hora_desde:9, hora_hasta:18)
			doctor1.addToJornadas(lunes1)
			doctor1.addToJornadas(martes1)
			doctor1.addToJornadas(miercoles1)
			doctor1.addToJornadas(jueves1)
			doctor1.addToJornadas(viernes1)
			doctor1.save(flush:true, failOnError:true)

			User doctor2 = new User(name:"Marcela Lopez", email:"marcela.lopez@me.com", password:"pw2", type: UserType.DOCTOR, especialidad:"Traumatologia", nacimiento:"1975-01-04", direccion:"Av Cabildo 3421")
			Jornada lunes2 = new Jornada(dia:Dia.LUNES, hora_desde:9, hora_hasta:18)
			Jornada martes2 = new Jornada(dia:Dia.MARTES, hora_desde:9, hora_hasta:18)
			Jornada miercoles2 = new Jornada(dia:Dia.MIERCOLES, hora_desde:9, hora_hasta:18)
			Jornada jueves2 = new Jornada(dia:Dia.JUEVES, hora_desde:9, hora_hasta:18)
			Jornada viernes2 = new Jornada(dia:Dia.VIERNES, hora_desde:9, hora_hasta:18)
			doctor2.addToJornadas(lunes2)
			doctor2.addToJornadas(martes2)
			doctor2.addToJornadas(miercoles2)
			doctor2.addToJornadas(jueves2)
			doctor2.addToJornadas(viernes2)
			doctor2.save(flush:true, failOnError:true)

			User paciente1 = new User(name:"Gaston Martinez", email:"gaston.martinez@me.com", password:"pw3", type: UserType.PATIENT, nacimiento:"1994-05-17", direccion:"Carlos Calvo 2945", plan_medico: "OSDE 310")
			paciente1.save(flush:true, failOnError:true)

		}


    }
    def destroy = {
    }
}
