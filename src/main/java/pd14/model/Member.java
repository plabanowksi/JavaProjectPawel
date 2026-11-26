package pd14.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pd14.exception.LoanLimitExceededException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Member {
    private final Long id;
    private final String name;
    private final String email;
    private final List<Loan> loans = new ArrayList<>();

    public boolean canBorrow() {
        if (this.loans.stream()
            .filter(loan -> loan.getReturnedAt() == null)
            .count() < 3) {

            return true;
        } else {
            throw new LoanLimitExceededException();
        }
    }
    public boolean hasValidEmail() {
        return this.email != null && this.email.contains("@");
    }
}
