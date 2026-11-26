package pd14.service;

import lombok.RequiredArgsConstructor;
import pd14.dto.CreateLoanCommand;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookCommand;
import pd14.exception.BookNotAvailableException;
import pd14.exception.LoanLimitExceededException;
import pd14.exception.LoanNotFoundException;
import pd14.model.Book;
import pd14.model.Loan;
import pd14.model.Member;
import pd14.repository.LoanRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class LoanService {

    private final MemberService memberService;
    private final BookService bookService;
    private final LoanRepository loanRepository;
    private static final int LOAN_PERIOD_DAYS = 7;

    public LoanDto borrow(CreateLoanCommand request) {

        Member member = memberService.findById(request.memberId());
        Book book = bookService.findById(request.bookId());

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException();
        }

        if (!member.canBorrow()) {
            throw new LoanLimitExceededException();
        }

        book.borrow();

        Long loanId = loanRepository.generateId();

        LocalDateTime borrowedAt = LocalDateTime.now();
        LocalDate dueDate = LocalDate.now().plusDays(LOAN_PERIOD_DAYS);

        Loan loan = new Loan(
            loanId,
            book,
            member,
            borrowedAt,
            dueDate,
            null,
            true
        );

        loanRepository.save(loan);

        return toDto(loan);
    }

    public void returnBook(ReturnBookCommand request) {

        Loan loan = loanRepository.findById(request.loanId());

        if (loan == null) {
            throw new LoanNotFoundException(request.loanId());
        }

        if (loan.isActive()) {
            throw new IllegalStateException("Book already returned.");
        }
        returnBook(loan);
    }

    public List<LoanDto> findOverdue() {
        return loanRepository.findAll().stream()
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

    private void returnBook(Loan loan) {
        loan.setReturnedAt(LocalDateTime.now());
        loan.getBook().returnBack();
        loan.setActive(false);
    }
}