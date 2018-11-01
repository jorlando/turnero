package turnero

import utils.UserType

class Users {
	String fistName
	String lastName
	String alias
	String email
	String password
	UserType type
	

    static constraints = {
    }

    def toMap(){
    	[
    		fist_name: fistName,
			last_name:lastName,
			alias:alias,
			email:email,
			password:password,
			type:type.toString()
		]
    }
}
