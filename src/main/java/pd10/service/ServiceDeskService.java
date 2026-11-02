package pd10.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pd10.model.*;
import pd10.repository.TicketRepository;
import pd10.repository.UserRepository;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ServiceDeskService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public User registerUser(String name, String role) {
        User user = new User(0, name, role);
        return userRepository.save(user);
    }

    public Ticket registerTicket(long id, String title, String description, TicketStatus status, Priority priority, Category category, User reporter, User assignee) {
        Ticket ticket = new Ticket(id, title, description, status, priority, category, reporter, assignee);
        return ticketRepository.save(ticket);
    }

    public void assignTicket(long ticketId, User employee) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));
        User user = userRepository.findById(employee.getId()).orElseThrow(() -> new IllegalArgumentException("Can't find user with ID: " + employee.getId()));
        ticket.setAssignee(user);
        ticketRepository.save(ticket);
    }

    public void startWorkingOnTicket(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));
        if (ticket.getStatus() != TicketStatus.NEW){
            throw new IllegalArgumentException("You can only start working on ticket withs status NEW");
        }
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        ticketRepository.save(ticket);
    }

    public void closeTicket(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));
        if (ticket.getStatus() == TicketStatus.CLOSED){
            throw new IllegalArgumentException("You can only close ticket with status INPROGRESS or NEW");
        }
        ticket.setStatus(TicketStatus.CLOSED);
        ticketRepository.save(ticket);
    }

    public String getTicketDetails(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));

        return ticket.getDescription();
    }

    public List<Ticket> filterAndSortTickets(TicketStatus status, Category category, long employeeId) {
        return ticketRepository.findAll().stream()
            .filter(ticket -> status ==null || ticket.getStatus() == status).
            filter(ticket -> category == null || ticket.getCategory() == category)
            .filter( ticket -> ticket.getAssignee() != null && ticket.getAssignee().getId() == employeeId)
            .sorted(Comparator.comparing(Ticket::getId))
            .toList();

    }

}
