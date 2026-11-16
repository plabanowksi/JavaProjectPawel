package pd12.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class OrderResult {
    private String clientName;
    private int orderedQuantity;
    private boolean orderCompleted;
    private String resultMessage;
}
