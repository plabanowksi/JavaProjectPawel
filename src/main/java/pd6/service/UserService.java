package pd6.service;

import lombok.AllArgsConstructor;

import pd6.repository.InMemoryRepository;
import pd6.model.User;

import java.util.Optional;

@AllArgsConstructor
public class UserService {
    private final InMemoryRepository<Long, User> userStorage;

    public void changeName(Long id, String newName){
        Optional<User> userOptional = userStorage.findById(id);
        userOptional.ifPresent(user -> user.setName(newName));
    }
}
