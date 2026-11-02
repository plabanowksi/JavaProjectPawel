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
    private String title;
    private String description;
    private TicketStatus status;
    private Priority priority;
    private Category category;
    private User reporter;
    private User assignee;
}
