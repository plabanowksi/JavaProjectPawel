package pd14;

import pd14.controller.BookController;
import pd14.controller.LoanController;
import pd14.controller.MemberController;
import pd14.dto.*;
import pd14.repository.BookRepository;
import pd14.repository.LoanRepository;
import pd14.repository.MemberRepository;
import pd14.service.BookService;
import pd14.service.LoanService;
import pd14.service.MemberService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MemberRepository memberRepository = new MemberRepository();
        BookRepository bookRepository = new BookRepository();
        LoanRepository loanRepository = new LoanRepository();

        BookService bookService = new BookService(bookRepository);
        MemberService memberService = new MemberService(memberRepository);

        LoanService loanService = new LoanService(memberService, bookService, loanRepository);

        BookController bookController = new BookController(bookService);
        MemberController memberController = new MemberController(memberService);
        LoanController loanController = new LoanController(loanService);

        BookDto book = bookController.addBook("978-83-01-00001-1", "Wiedźmin", "Andrzej Sapkowski", 1993, 2);
        MemberDto member = memberController.register("Anna Nowak", "anna@example.com");

        LoanDto loan = loanController.borrowBook(new CreateLoanCommand(member.id(), book.id()));
        System.out.println("Loan: " + loan);

        List<LoanDto> activeLoans = memberController.getActiveLoans(member.id());
        System.out.println("Active loans: " + activeLoans);

        loanController.returnBook(new ReturnBookCommand(loan.id()));
        System.out.println("Available copies: " + bookController.findByIsbn(book.isbn()).availableCopies());

        List<LoanDto> overdue = loanController.getOverdueLoans();
        System.out.println("Overdue: " + overdue);
    }
}
