package pd14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pd14.exception.BookNotAvailableException;

@AllArgsConstructor
@Getter
@Setter
public class Book {
    private final Long id;
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private int availableCopies;

    private void borrow() {
        if (this.availableCopies > 0) {
            this.availableCopies = this.availableCopies - 1;
        } else {
            throw new BookNotAvailableException();
        }
    }

    public void returnBack() {
        this.availableCopies++;
    }
}