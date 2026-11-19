package pd14.dto;

import pd14.model.Book;
import pd14.model.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanDto(
        Long id,
        Book book,
        Member member,
        LocalDateTime borrowedAt,
        LocalDate dueDate,
        LocalDateTime returnedAt) {
}
