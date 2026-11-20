package pd14.service;

import pd14.dto.CreateLoanRequest;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookRequest;
import pd14.exception.BookNotAvailableException;
import pd14.exception.LoanLimitExceededException;
import pd14.exception.LoanNotFoundException;
import pd14.model.Book;
import pd14.model.Loan;
import pd14.model.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LoanService {

    private final MemberService memberService;
    private final BookService bookService;
    private final Map<Long, Loan> loans = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public LoanService(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    public LoanDto borrow(CreateLoanRequest request) {
        Member member = memberService.findById(request.memberId());
        Book book = bookService.findByid(request.bookId());

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException();
        }

        if (!member.canBorrow()) {
            throw new LoanLimitExceededException();
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        Long loanId = idGenerator.getAndIncrement();
        LocalDateTime borrowedAt = LocalDateTime.now();
        LocalDate dueDate = LocalDate.now().plusDays(7);

        Loan loan = new Loan(loanId, book, member, borrowedAt, dueDate, null);
        loans.put(loanId, loan);
        return toDto(loan);
    }

    public void returnBook(ReturnBookRequest request) {
        Loan loan = loans.get(request.loanId());
        if (loan == null) {
            throw new LoanNotFoundException(request.loanId());
        }
        if (loan.getReturnedAt() != null) {
            throw new IllegalStateException("Book already returned.");
        }
        loan.setReturnedAt(LocalDateTime.now());
        loan.getBook().returnBack();
    }

    public List<LoanDto> findOverdue() {
        return loans.values().stream()
            .filter(Loan::isOverdue)
            .map(this::toDto)
            .toList();
    }

    private LoanDto toDto(Loan loan) {
        return new LoanDto(
            loan.getId(),
            loan.getBook(),
            loan.getMember(),
            loan.getBorrowedAt(),
            loan.getDueDate(),
            loan.getReturnedAt()
        );
    }
}
