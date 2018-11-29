class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/user"(controller: "user") {
            action = [POST: "create"]
        }

        "/appointment"(controller: "user") {
            action = [POST: "createTurno"]
        }

        "/appointment/$turnoId"(controller: "user") {
            action = [DELETE: "cancelTurno"]
        }

        "/doctors"(controller: "user") {
            action = [GET: "findDoctors"]
        }
        "/home/$userId"(controller: "user") {
            action = [GET: "home"]
        }

        "/login"(controller: "user") {
            action = [POST: "login"]
        }


        "/"(view:"/index")
        "500"(controller: "error"){ action = [GET:"index"] }
	}
}
