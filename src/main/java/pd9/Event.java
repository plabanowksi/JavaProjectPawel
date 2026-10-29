package pd9;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class Event {
    private final UUID id= UUID.randomUUID();
    private final String name;
    private final EventStatus eventStatus;
    private final String description;
}