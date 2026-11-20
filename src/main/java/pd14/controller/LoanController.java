package pd14.controller;

import lombok.RequiredArgsConstructor;
import pd14.dto.CreateLoanRequest;
import pd14.dto.LoanDto;
import pd14.dto.ReturnBookRequest;
import pd14.service.LoanService;

import java.util.List;

@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    public LoanDto borrowBook(CreateLoanRequest request) {
        return loanService.borrow(request);
    }

    public void returnBook(ReturnBookRequest request) {
        loanService.returnBook(request);
    }

    public List<LoanDto> getOverdueLoans() {
        return loanService.findOverdue();
    }
}
