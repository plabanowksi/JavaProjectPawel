package pd14.repository;

import pd14.model.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LoanRepository {

    private final Map<Long, Loan> loans = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Long generateId() {
        return idGenerator.getAndIncrement();
    }

    public void save(Loan loan) {
        loans.put(loan.getId(), loan);
    }

    public Loan findById(Long id) {
        return loans.get(id);
    }

    public List<Loan> findAll() {
        return new ArrayList<>(loans.values());
    }
}