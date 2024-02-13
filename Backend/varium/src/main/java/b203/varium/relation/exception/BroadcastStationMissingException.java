package b203.varium.relation.exception;

public class BroadcastStationMissingException extends RuntimeException {
    public BroadcastStationMissingException() {
        super();
    }

    public BroadcastStationMissingException(String message) {
        super(message);
    }

    public BroadcastStationMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BroadcastStationMissingException(Throwable cause) {
        super(cause);
    }
}
