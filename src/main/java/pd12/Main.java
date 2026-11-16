package pd12;

import pd12.model.Order;
import pd12.model.OrderResult;
import pd12.service.OrderService;
import pd12.store.Store;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order("Adam", 2),
            new Order("Kasia", 3),
            new Order("Marek", 1),
            new Order("Ola", 4),
            new Order("Piotr", 2),
            new Order("Anna", 3),
            new Order("Tomasz", 1),
            new Order("Julia", 5),
            new Order("Michał", 2),
            new Order("Natalia", 2)
        );
        Store store = new Store(10);
        OrderService orderService = new OrderService(store);

        List<CompletableFuture<OrderResult>> orderResults = orders.stream()
            .map(orderService::processOrder)
            .toList();

        CompletableFuture.allOf(
            orderResults.toArray(new CompletableFuture[0])
        ).join();

        orderResults.forEach(future -> {
            OrderResult result = future.join();
            System.out.println(result);
        });

        int remainingQuantity = store.getAvailableQuantity();
        int soldQuantity = 10 - remainingQuantity;

        System.out.println("Summary");
        System.out.println("Remaining laptops: " + remainingQuantity);
        System.out.println("Solded laptops:" + soldQuantity);
    }
}
