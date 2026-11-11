package pd12.service;

import lombok.AllArgsConstructor;
import pd12.model.Order;
import pd12.model.OrderResult;
import pd12.store.Store;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class OrderService {
    private final Store store;
    public CompletableFuture<OrderResult> processOrder(Order order) {

        return CompletableFuture.supplyAsync(() -> {
            boolean success = store.purchase(order.getQuantity());
            if (!success) {
                return new OrderResult(order.getName(), order.getQuantity(), false, "Fail - order can't be processed");
            } else {
                return new OrderResult(order.getName(), order.getQuantity(), success, "The order has been processed successfully");
            }
        });
    }
}
