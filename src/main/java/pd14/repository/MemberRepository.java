package pd14.repository;

import pd14.model.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class MemberRepository {

    private final Map<Long, Member> members = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Long generateId() {
        return idGenerator.getAndIncrement();
    }

    public void save(Member member) {
        members.put(member.getId(), member);
    }

    public Member findById(Long id) {
        return members.get(id);
    }
}