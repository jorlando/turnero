package turnero

import exceptions.BadRequestsException
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ErrorController)
class ErrorControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test index with status code and message"() {
        given:
        def statusExpected = 400
        controller.request.exception = new BadRequestsException("custom message")
        when:
        controller.index()
        then:
        response.json.message == "custom message"
        response.status == statusExpected
    }

    void "test index without status code or message"() {
        given:
        def statusExpected = 500
        controller.request.exception = new RuntimeException()
        when:
        controller.index()
        then:
        response.json.status == statusExpected
        response.json.message == "Internal server error processing "
        response.status == statusExpected
    }

    void "test index without status code or message, with forwardURI"() {
        given:
        def statusExpected = 500
        controller.request.exception = new RuntimeException()
        controller.request.forwardURI = "/test"
        when:
        controller.index()
        then:
        response.json.status == statusExpected
        response.json.message == "Internal server error processing /test"
        response.status == statusExpected
    }
}
