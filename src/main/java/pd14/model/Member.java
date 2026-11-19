package pd14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pd14.exception.LoanLimitExceededException;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Member {
    private final Long id;
    private final String name;
    private final String email;
    private List<Loan> loans;

    private boolean canBorrow() {
        if (this.loans.stream()
                .filter(loan -> loan.getReturnedAt() == null)
                .count() < 3) {

            return true;
        } else {
            throw new LoanLimitExceededException();
        }
    }
}
