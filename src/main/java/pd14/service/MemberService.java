package pd14.service;

import pd14.exception.MemberNotFoundException;
import pd14.model.Loan;
import pd14.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemberService {
    private final Map<Long, Member> members = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Member register(String name, String email) {
        Long memberId = idGenerator.getAndIncrement();
        Member member = new Member(memberId, name, email, new ArrayList<>());
        members.put(memberId, member);
        return member;
    }

    public Member findById(Long memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException(memberId);
        }
        return member;
    }

    public List<Loan> getActiveLoans(Long memberId) {
        return members.get(memberId).getLoans();
    }
}
