package pd14.dto;

public record CreateLoanCommand(
    Long bookId,
    Long memberId) {
}
