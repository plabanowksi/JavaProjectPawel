package pd14.exception;

public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException() {
        super("User can't have more than 3 loan!");
    }
}
