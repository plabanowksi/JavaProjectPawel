package pd14.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pd14.exception.BookNotFoundException;
import pd14.model.Book;
import pd14.repository.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book createBook(Long id, String isbn, String title, String author, int year, int copies) {
        return new Book(id, isbn, title, author, year, copies);
    }

    @Test
    void shouldAddBookAndSaveToRepository() {
        when(bookRepository.generateId()).thenReturn(11L);

        Book book = bookService.addBook(
            "21-232-2323-44",
            "Wiedzmin",
            "author",
            2005,
            6
        );

        assertNotNull(book);
        assertEquals(11L, book.getId());
        assertEquals("Wiedzmin", book.getTitle());
        assertEquals(6, book.getAvailableCopies());
        verify(bookRepository).generateId();
        verify(bookRepository).save(book);
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenIsbnIsNull() {
        assertThrows(BookNotFoundException.class, () -> bookService.findByIsbn(null));
        verifyNoInteractions(bookRepository);
    }

    @Test
    void shouldReturnBookWhenFoundByIsbn(){
        Book book = createBook(4L,"2323-2323","sdasd","dasda",2005,5);
        when(bookRepository.findAll()).thenReturn(List.of(book));
        Book found = bookService.findByIsbn("2323-2323");

        assertEquals(book, found);
    }
    @Test
    void shouldThrowIBookNotFoundExceptionWhenBookIdIsNull() {
        assertThrows(BookNotFoundException.class, () -> bookService.findById(null));
        verifyNoInteractions(bookRepository);
    }

    @Test
    void shouldReturnBookWhenFoundById(){
        Book book = createBook(4L,"2323-2323","sdasd","dasda",2005,5);
        when(bookRepository.findById(4L)).thenReturn(book);
        Book found = bookService.findById(4L);

        assertEquals(book, found);
    }
    
    @Test
    void shouldReturnOnlyBooksWithAvailableCopiesGreaterThanZero() {
        Book available = createBook(1L, "111", "Book A", "Author A", 2020, 2);
        Book unavailable = createBook(2L, "222", "Book B", "Author B", 2021, 0);
        when(bookRepository.findAll()).thenReturn(List.of(available, unavailable));

        List<Book> result = bookService.findAvailable();

        assertEquals(List.of(available), result);
    }
}