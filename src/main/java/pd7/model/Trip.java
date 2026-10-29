package pd7.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Trip {
    private Destination destination;
    private TransportType transport;
    private double price;
    private int duration;
}
