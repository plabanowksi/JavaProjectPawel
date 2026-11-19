package pd14.dto;

public record CreateLoanRequest(
        Long bookId,
        Long memberId) {
}
