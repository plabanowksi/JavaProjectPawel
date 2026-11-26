package pd14.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    private Loan createLoan(LocalDate dueDate, LocalDateTime returnedAt) {
        return new Loan(
            1L,
            null,
            null,
            LocalDateTime.now().minusDays(10),
            dueDate,
            returnedAt,
            returnedAt == null
        );
    }

    @Test
    void shouldReturnTrueWhenDueDateIsInPastAndNotReturned(){
        Loan loan = createLoan(LocalDate.now().minusDays(1), null);
        assertTrue(loan.isOverdue());
    }

    @Test
    void shouldReturnFalseWhenDueDateIsToday(){
        Loan loan = createLoan(LocalDate.now(), null);
        assertFalse(loan.isOverdue());
    }

    @Test
    void shouldReturnFalseWhenAlreadyReturned(){
        Loan loan = createLoan(LocalDate.now().minusDays(4), LocalDateTime.now().minusDays(1));
        assertFalse(loan.isOverdue());
    }

    @Test
    void shouldReturnFalseWhenDueDateIsNull() {
        Loan loan = createLoan(null, null);
        assertFalse(loan.isOverdue());
    }
}