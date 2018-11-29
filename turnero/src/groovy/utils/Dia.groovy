package utils

/**
 * Created by jorlando on 12/11/2018.
 */
enum Dia {
    DOMINGO(0),LUNES(1),MARTES(2),MIERCOLES(3),JUEVES(4),VIERNES(5),SABADO(6)
    private Integer numero

    Dia(int numero){
        this.numero = numero
    }

    public static Dia getDia(searchDia){
    	return valueOf(searchDia?.toUpperCase())
    }

    public static Dia findInt(Integer diaInt){
        values().find{it.numero == diaInt}

    }
}
