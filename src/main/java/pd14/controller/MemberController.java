package pd14.controller;

import lombok.RequiredArgsConstructor;
import pd14.dto.LoanDto;
import pd14.dto.MemberDto;
import pd14.model.Loan;
import pd14.model.Member;
import pd14.service.MemberService;

import java.util.List;

@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    public MemberDto register(String name, String email) {
        return memberService.register(name, email);
    }

    public MemberDto findById(Long memberId) {
        Member member = memberService.findById(memberId);
        return new MemberDto(
            member.getId(),
            member.getName(),
            member.getEmail()
        );
    }

    public List<LoanDto> getActiveLoans(Long memberId) {
        return memberService.getActiveLoans(memberId).stream()
            .map(this::toLoanDto)
            .toList();
    }

    private LoanDto toLoanDto(Loan loan) {
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
