package pd14.controller;

import lombok.RequiredArgsConstructor;
import pd14.dto.CreateLoanCommand;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookCommand;
import pd14.service.LoanService;

import java.util.List;

@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    public LoanDto borrowBook(CreateLoanCommand request) {
        return loanService.borrow(request);
    }

    public void returnBook(ReturnBookCommand request) {
        loanService.returnBook(request);
    }

    public List<LoanDto> getOverdueLoans() {
        return loanService.findOverdue();
    }
}
