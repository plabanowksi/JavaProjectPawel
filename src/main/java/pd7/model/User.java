package pd7.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private String name;
    private TransportType preferredTransport;
    private double budget;
}
