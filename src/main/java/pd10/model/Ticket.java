package pd10.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Ticket {
    private long id;
    private final String title;
    private final String description;
    private TicketStatus status;
    private Priority priority;
    private Category category;
    private final long reporter;
    private long assignee;

    public boolean isClosed() {
        return this.status == null || this.getStatus() == TicketStatus.CLOSED;
    }

    public boolean isOpen() {
        return this.status == null || this.getStatus() != TicketStatus.CLOSED;
    }

    public void markClosed() {
        this.status = TicketStatus.CLOSED;
    }

    public boolean isUnassigned() {
        return this.getAssignee() == 0L;
    }
}
