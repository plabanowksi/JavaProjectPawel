package pd14.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {
        super("There is not available copies");
    }
}
