package pd14.service;

import pd14.exception.BookNotFoundException;
import pd14.model.Book;
import pd14.repository.BookRepository;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(String isbn, String title, String author,
                        int year, int availableCopies) {

        Long bookId = bookRepository.generateId();

        Book book = new Book(bookId, isbn, title, author, year, availableCopies);

        bookRepository.save(book);

        return book;
    }

    public Book findByIsbn(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("Isbn can't be empty.");
        }

        return bookRepository.findAll().stream()
            .filter(book -> isbn.equals(book.getIsbn()))
            .findFirst()
            .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book findById(Long bookId) {
        Book book = bookRepository.findById(bookId);

        if (book == null) {
            throw new BookNotFoundException(bookId);
        }

        return book;
    }

    public List<Book> findAvailable() {
        return bookRepository.findAll().stream()
            .filter(book -> book.getAvailableCopies() > 0)
            .toList();
    }

    public List<Book> search(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String normalizeQuery = query.toLowerCase().trim();

        return bookRepository.findAll().stream()
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