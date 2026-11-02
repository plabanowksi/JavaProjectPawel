package pd10.repository;

import pd10.model.Category;
import pd10.model.Priority;
import pd10.model.Ticket;
import pd10.model.TicketStatus;
import pd10.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTicketRepository implements TicketRepository{
    private final Map<Long, Ticket> storage = new HashMap<>();

    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Ticket save(Ticket ticket) {
        if (ticket.getId() == 0) {
            ticket.setId(idCounter.getAndIncrement());
        }

        storage.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Ticket> findByStatus(TicketStatus status) {
        return storage.values().stream()
            .filter(ticket -> ticket.getStatus() == status)
            .toList();
    }

    @Override
    public List<Ticket> findByCategory(Category category) {
        return storage.values().stream()
            .filter(ticket -> ticket.getCategory() == category)
            .toList();
    }

    @Override
    public List<Ticket> findByAssignee(User assignee) {
        if (assignee == null) {
            return findByPriorityAndAssigneeIsNull(null);
        }
        return storage.values().stream()
            .filter(ticket -> ticket.getAssignee() != null
                && ticket.getAssignee().getId() == assignee.getId())
            .toList();
    }

    @Override
    public List<Ticket> findByPriorityAndAssigneeIsNull(Priority priority) {
        return storage.values().stream()
            .filter(ticket -> ticket.getPriority() == priority)
            .filter(ticket -> ticket.getAssignee() == null)
            .toList();
    }

}
