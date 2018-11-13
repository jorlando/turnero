package turnero

import grails.converters.JSON
import org.codehaus.groovy.grails.web.errors.ExceptionUtils

class ErrorController {

    def index = {
        def eResponse = [message:"Internal server error processing $request.forwardURI", status: 500]
        try{
            Throwable exception = ExceptionUtils.getRootCause(request.exception as Exception)
            if(exception?.message){
                eResponse.message = exception?.message
            }
            if(exception?.hasProperty("status")){
                eResponse.status = exception.status
            }

            response.setStatus(eResponse.status)
        }catch(e){
            log.info("ErrorController. Exception: ${e.getClass().toString()} - message: ${e.getMessage()} - uri: $request.forwardURI")
            eResponse.message = "ErrorController. Exception: ${e.getClass().toString()} - message: ${e.getMessage()} - uri: $request.forwardURI"
            eResponse.status = 500
        }
        response.setStatus(eResponse.status)
        render eResponse as JSON
    }
}
