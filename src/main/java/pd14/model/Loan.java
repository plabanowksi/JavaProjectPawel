package pd14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Loan {
    private final Long id;
    private final Book book;
    private final Member member;
    private final LocalDateTime borrowedAt;
    private LocalDate dueDate;
    private LocalDateTime returnedAt;

    public boolean isOverdue() {
        return this.returnedAt == null
            && this.dueDate != null
            && this.dueDate.isBefore(LocalDate.now());
    }
}
