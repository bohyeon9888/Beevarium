package b203.varium.relation.exception;

public class UserMissingException extends RuntimeException {

    public UserMissingException() {
        super();
    }

    public UserMissingException(String message) {
        super(message);
    }

    public UserMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserMissingException(Throwable cause) {
        super(cause);
    }
}
