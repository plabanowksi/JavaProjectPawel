package pd14.service;

import lombok.RequiredArgsConstructor;
import pd14.dto.MemberDto;
import pd14.exception.MemberNotFoundException;
import pd14.model.Loan;
import pd14.model.Member;
import pd14.repository.MemberRepository;

import java.util.List;

@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto register(String name, String email) {
        Long memberId = memberRepository.generateId();

        Member member = new Member(memberId, name, email);
        member.hasValidEmail();
        memberRepository.save(member);

        return new MemberDto(memberId, name, email);
    }

    public Member findById(Long memberId) {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new MemberNotFoundException(memberId);
        }

        return member;
    }

    public List<Loan> getActiveLoans(Long memberId) {
        Member member = findById(memberId);
        return member.getLoans();
    }
}