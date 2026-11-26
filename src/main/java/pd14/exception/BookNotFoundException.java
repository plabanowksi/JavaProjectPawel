package pd14.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Book not found: " + isbn);
    }

    public BookNotFoundException(Long bookId) {
        super("Book not found: " + bookId);
    }
}
