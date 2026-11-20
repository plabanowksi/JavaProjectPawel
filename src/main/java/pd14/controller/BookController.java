package pd14.controller;

import lombok.RequiredArgsConstructor;
import pd14.dto.BookDto;
import pd14.model.Book;
import pd14.service.BookService;

import java.util.List;

@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    public BookDto addBook(String isbn, String title, String author, int year, int availableCopies){
        Book book = bookService.addBook(isbn, title, author, year, availableCopies);
        return toDto(book);
    }
    public BookDto findByIsbn(String isbn) {
        Book book = bookService.findByIsbn(isbn);
        return toDto(book);
    }

    public List<BookDto> findAvailable() {
        return bookService.findAvailable().stream()
            .map(this::toDto)
            .toList();
    }

    public List<BookDto> search(String query) {
        return bookService.search(query).stream()
            .map(this::toDto)
            .toList();
    }

    private BookDto toDto(Book book) {
        return new BookDto(
            book.getId(),
            book.getIsbn(),
            book.getTitle(),
            book.getAuthor(),
            book.getYear(),
            book.getAvailableCopies()
        );
    }
}
