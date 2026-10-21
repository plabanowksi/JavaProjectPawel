package pd6;

import pd6.repository.InMemoryRepository;
import pd6.model.Product;
import pd6.model.User;
import pd6.service.UserService;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Long, User> users = new InMemoryRepository<>();
        InMemoryRepository<String, Product> products = new InMemoryRepository<>();

        users.save(new User(1L, "Jan"));
        users.save(new User(2L, "Janek"));
        products.save(new Product("P-101","Beer"));

        System.out.println(users.findById(1L));
        System.out.println(products.findById("P-101"));

        System.out.println(users.getAll());

        users.deleteById(1L);
        System.out.println(users.findById(1L));

        UserService userService = new UserService(users);
        userService.changeName(2L,"Kasia");

        System.out.println(users.findById(2L));
    }
}
