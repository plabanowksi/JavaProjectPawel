package pd6.service;

import lombok.RequiredArgsConstructor;

import pd6.repository.EntityStorage;
import pd6.model.User;
import util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final EntityStorage<Long, User> userStorage;

    public void changeName(Long id, String newName){
        Optional<User> userOptional = userStorage.findById(id);
        userOptional.ifPresent(user -> user.setName(newName));
    }
}
