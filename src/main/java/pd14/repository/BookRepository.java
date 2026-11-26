package pd14.repository;

import pd14.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BookRepository {

    private final Map<Long, Book> books = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Long generateId() {
        return idGenerator.getAndIncrement();
    }

    public void save(Book book) {
        books.put(book.getId(), book);
    }

    public Book findById(Long id) {
        return books.get(id);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}