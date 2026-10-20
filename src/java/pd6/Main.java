

import pd6.repository.EntityStorage;
import pd6.model.Productt;
import pd6.model.User;
import pd6.service.UserService;

public class Main {
    public static void main(String[] args) {
        EntityStorage<Long, User> users = new EntityStorage<>();
        EntityStorage<String, Productt> products = new EntityStorage<>();

        users.save(new User(1L, "Jan"));
        users.save(new User(2L, "Janek"));
        products.save(new Productt("P-101","Beer"));

        System.out.println(users.findById(1L));
        System.out.println(products.findById("P-101"));

        System.out.println(users.getAll());

        users.delete(1L);
        System.out.println(users.findById(1L));

        UserService userService = new UserService(users);
        userService.changeName(2L,"Kasia");

        System.out.println(users.findById(2L));

    }
}
