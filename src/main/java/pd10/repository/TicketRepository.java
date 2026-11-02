package pd10.repository;

import pd10.model.Category;
import pd10.model.Priority;
import pd10.model.Ticket;
import pd10.model.TicketStatus;
import pd10.model.User;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket save(Ticket ticket);

    Optional<Ticket> findById(long id);

    List<Ticket> findAll();

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByCategory(Category category);

    List<Ticket> findByAssignee(User assignee);

    List<Ticket> findByPriorityAndAssigneeIsNull(Priority priority);

}
