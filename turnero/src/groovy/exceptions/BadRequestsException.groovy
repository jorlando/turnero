package exceptions

/**
 * Created by jorlando on 12/11/2018.
 */

class BadRequestsException extends RuntimeException {
    def status = 400

    public BadRequestsException() {
    }

    public BadRequestsException(String arg0) {
        super(arg0);
    }
}
