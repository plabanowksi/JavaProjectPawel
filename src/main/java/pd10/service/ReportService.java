package pd10.service;

import pd10.model.Category;
import pd10.model.Priority;
import pd10.model.Ticket;
import pd10.model.TicketStatus;
import pd10.model.User;
import pd10.repository.TicketRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {

    private final TicketRepository ticketRepository;

    public ReportService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Map<TicketStatus, Map<Category, Long>> getTicketsCountByStatusAndCategory() {
        return ticketRepository.findAll().stream()
            .collect(Collectors.groupingBy(
                Ticket::getStatus,
                Collectors.groupingBy(Ticket::getCategory, Collectors.counting())
            ));
    }

    public Map<User, Long> getActiveTicketsCountPerEmployee() {
        return ticketRepository.findAll().stream()
            .filter(t -> t.getAssignee() != null)
            .filter(t -> t.getStatus() != TicketStatus.CLOSED)
            .collect(Collectors.groupingBy(
                Ticket::getAssignee,
                Collectors.counting()
            ));
    }

    public List<Ticket> getUnassignedHighPriorityTickets() {
        return ticketRepository.findByPriorityAndAssigneeIsNull(Priority.HIGH);

    }

    public boolean areAllTicketsClosed() {
        List<Ticket> allTickets = ticketRepository.findAll();
        if (allTickets.isEmpty()) {
            return true;
        }
        return allTickets.stream()
            .allMatch(t -> t.getStatus() == TicketStatus.CLOSED);
    }
}