package pd10.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pd10.model.*;
import pd10.repository.TicketRepository;
import pd10.repository.UserRepository;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ServiceDeskService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private User registerUser(String name, String role) {
        User user = new User(0, name, role);
        return userRepository.save(user);
    }

    private Ticket registerTicket(long id, String title, String description, TicketStatus status, Priority priority, Category category, User reporter, User assignee) {
        Ticket ticket = new Ticket(id, title, description, status, priority, category, reporter, assignee);
        return ticketRepository.save(ticket);
    }

    private void assignTicket(long ticketId, long employeeId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));
        User user = userRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Can't find user with ID: " + employeeId));
        ticket.setAssignee(user);
        ticketRepository.save(ticket);
    }

    private void startWorkingOnTicket(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));
        if (ticket.getStatus() != TicketStatus.NEW){
            throw new IllegalArgumentException("You can only start working on ticket withs status NEW");
        }
        ticket.setStatus(TicketStatus.IN_PROGRESS);
        ticketRepository.save(ticket);
    }

    private void closeTicket(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));
        if (ticket.getStatus() != TicketStatus.CLOSED){
            throw new IllegalArgumentException("You can only close ticket with status INPROGRESS or NEW");
        }
        ticket.setStatus(TicketStatus.CLOSED);
        ticketRepository.save(ticket);
    }

    private String getTicketDetails(long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException("Can't find ticket with ID: " + ticketId));

        return ticket.getDescription();
    }

    private List<Ticket> filterAndSortTickets(TicketStatus status, Category category, long employeeId) {
        return null;
    }

}
