package pd15.exception;

public class PostFetchException extends RuntimeException {
    public PostFetchException(String message) {
        super(message);
    }

    public PostFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
