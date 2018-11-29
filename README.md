# Turnero
Trabajo practico para 75.18 - Proyectos Informáticos FIUBA

## Instalacion:

### Instalar SDK man: https://sdkman.io/
	curl -s "https://get.sdkman.io" | bash
	source "$HOME/.sdkman/bin/sdkman-init.sh"
	
### Instalar Java 8:
	sdk install java 8.0.191-oracle
* Solo si no tienen java 8


### Instalar Grails:
	sdk install grails 2.5.6
* Verificar que instala la version 2.5.6

## Seteo de versiones:
	sdk use java 8.0.191-oracle
	sdk use grails 2.5.6
	
* Si no setean estas versiones por default y tiene que abrir otra terminal se va a perder lo que tienen seteado y tiene que setearlo a mano de esta manera.

## Uso de grails:
* grails test-app: corre los test
* grails clean: limpia lo compilado y baja dependencias
* grails compile: compila la app
* grails run-app: Levanta la app y la deja disponible para pegarle desde el browser (http://localhost:8080)

Crear un user:
	http://localhost:8080/users/create?alias=miAlias&name=miName&lastName=miLastName&email=miEmail&password=miPass

Login valido: 
	http://localhost:8080/users/login?alias=miAlias&password=miPass
	
Login Invalido:
	http://localhost:8080/users/login?alias=miAlias&password=otraPass


## Explicacion:
Los requests entran por el UrlMappings que es quien rutea las llamadas (https://github.com/jorlando/turnero/blob/master/grails-app/controllers/turnero/UrlMappings.groovy) ahi se fija la regla, por default entra en "/$controller/$action?/" donde primero esta el nombre del controller y despues el nombre del metodo a ejecutar.


### Ejemplo url de login:

http://localhost:8080/users/login?alias=miAlias&password=miPass 

Va al controller Users https://github.com/jorlando/turnero/blob/master/grails-app/controllers/turnero/UsersController.groovy y busca el metodo login. En params viene todo lo que paso por parametro de url, llama al service (https://github.com/jorlando/turnero/blob/master/grails-app/services/turnero/UsersService.groovy) que es el encargado de manejar los objetos de la base de datos (toda app de grails tiene una base en memoria) y hace el render de una vista (https://github.com/jorlando/turnero/blob/master/grails-app/views/users/loginOk.gsp)


