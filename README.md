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
	sdk install grails
* Verificar que instala la version 3.3.8

## Seteo de versiones:
	sdk use java 8.0.191-oracle
	sdk use grails 3.3.8
	
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

