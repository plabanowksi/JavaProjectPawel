package pd14.controller;

import pd14.dto.LoanDto;
import pd14.dto.MemberDto;
import pd14.model.Loan;
import pd14.model.Member;
import pd14.service.MemberService;

import java.util.List;

public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public MemberDto register(String name, String email) {
        Member member = memberService.register(name, email);
        return toMemberDto(member);
    }

    public MemberDto findById(Long memberId) {
        Member member = memberService.findById(memberId);
        return toMemberDto(member);
    }

    public List<LoanDto> getActiveLoans(Long memberId) {
        return memberService.getActiveLoans(memberId).stream()
            .map(this::toLoanDto)
            .toList();
    }

    private MemberDto toMemberDto(Member member) {

        return new MemberDto(
            member.getId(),
            member.getName(),
            member.getEmail()
        );
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
