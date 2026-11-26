package pd14.model;

import org.junit.jupiter.api.Test;
import pd14.exception.LoanLimitExceededException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    private Member createMember() {
        return new Member(1L, "Pawel", "pawel@gmail.com");
    }

    private Loan activeLoan(long id) {
        return new Loan(id,
            null,
            null,
            LocalDateTime.now().minusDays(5),
            LocalDate.now().plusDays(9),
            null,
            true);
    }

    private Loan returnedLoan(long id) {
        return new Loan(
            id,
            null,
            null,
            LocalDateTime.now().minusDays(20),
            LocalDate.now().minusDays(6),
            LocalDateTime.now().minusDays(7),
            false
        );
    }

    @Test
    void shouldReturnTrueWhenMemberHasFewerThanThreeActiveLoans() {
        Member member = createMember();
        member.getLoans().add(activeLoan(1L));
        member.getLoans().add(activeLoan(2L));

        assertTrue(member.canBorrow());
    }

    @Test
    void shouldThrowExceptionWhenMemberHasThreeActiveLoans() {
        Member member = createMember();
        member.getLoans().add(activeLoan(1L));
        member.getLoans().add(activeLoan(2L));
        member.getLoans().add(activeLoan(3L));

        assertThrows(LoanLimitExceededException.class, member::canBorrow);
    }

    @Test
    void shouldIgnoreReturnedLoansWhenCheckingBorrowLimit() {
        Member member = createMember();
        member.getLoans().add(returnedLoan(1L));
        member.getLoans().add(returnedLoan(2L));
        member.getLoans().add(activeLoan(3L));
        member.getLoans().add(activeLoan(4L));

        assertTrue(member.canBorrow());
    }

    @Test
    void shouldReturnFalseWhenEmailDoesNotContainAtSign() {
        Member member = new Member(1L, "Jan Kowalski", "jankowalski.example.com");

        assertFalse(member.hasValidEmail());
    }

    @Test
    void shouldReturnTrueWhenEmailContainsAtSign() {
        Member member = new Member(1L, "Jan Kowalski", "jan@example.com");

        assertTrue(member.hasValidEmail());
    }
}