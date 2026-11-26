package pd14.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String bookId) {
        super("Book not found: " + bookId);
    }

    public BookNotFoundException(Long bookId) {
        super("Book not found: " + bookId);
    }
}
