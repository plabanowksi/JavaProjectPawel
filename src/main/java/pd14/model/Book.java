package pd14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pd14.exception.BookNotAvailableException;

@AllArgsConstructor
@Getter
public class Book {
    private final Long id;
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private int availableCopies;

    public void borrow() {
        if (this.availableCopies > 0) {
            this.availableCopies -= 1;
        } else {
            throw new BookNotAvailableException();
        }
    }

    public void returnBack() {
        this.availableCopies++;
    }
}