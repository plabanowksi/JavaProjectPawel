package pd14.model;

import org.junit.jupiter.api.Test;
import pd14.exception.BookNotAvailableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {
    private Book createBook(int availableCopies) {
        return new Book(
            1L,
            "978-83-01-00000-1",
            "Clean Code",
            "Robert C. Martin",
            2008,
            availableCopies
        );
    }

    @Test
    void shouldDecreaseCopiesWhenBorrowed() {
        Book book = createBook(2);
        book.borrow();
        assertEquals(1, book.getAvailableCopies());
    }

    @Test
    void shouldThrowExceptionWhenBorrowEqualsZero() {
        Book book = createBook(0);
        assertThrows(BookNotAvailableException.class, book::borrow);
        assertEquals(0, book.getAvailableCopies());
    }

    @Test
    void shouldIncreaseAvailableCopiesWhenReturned() {
        Book book = createBook(1);
        book.returnBack();
        assertEquals(2, book.getAvailableCopies());
    }
}

