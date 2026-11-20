package pd14.service;

import pd14.exception.BookNotFoundException;
import pd14.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BookService {

    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Book addBook(String isbn, String title, String author, int year, int availableCopies) {
        Long bookId = idGenerator.getAndIncrement();
        Book book = new Book(bookId, isbn, title, author, year, availableCopies);
        books.put(bookId, book);
        return book;
    }

    public Book findByIsbn(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new BookNotFoundException(isbn);
        }
        return book;
    }

    public Book findByid(Long bookId) {
        Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotFoundException(bookId.toString());
        }
        return book;
    }

    public List<Book> findAvailable() {
        return books.values().stream()
            .filter(book -> book.getAvailableCopies() > 0).toList();
    }

    public List<Book> search(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String normalizeQuery = query.toLowerCase().trim();
        return books.values().stream()
            .filter(book -> matches(book, normalizeQuery))
            .toList();
    }

    private boolean matches(Book book, String query) {
        return containsIgnoreCase(book.getTitle(), query)
            || containsIgnoreCase(book.getAuthor(), query)
            || containsIgnoreCase(book.getIsbn(), query);
    }

    private boolean containsIgnoreCase(String field, String query) {
        return field != null && field.toLowerCase().contains(query);
    }
}
