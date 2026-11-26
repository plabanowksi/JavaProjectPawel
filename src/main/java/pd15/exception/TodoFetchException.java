package pd15.exception;

public class TodoFetchException extends RuntimeException {
    public TodoFetchException(String message) {
        super(message);
    }

    public TodoFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
