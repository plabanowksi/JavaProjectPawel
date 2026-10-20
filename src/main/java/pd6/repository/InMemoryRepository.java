package pd6.repository;

import lombok.AllArgsConstructor;
import lombok.ToString;
import pd6.model.Identifiable;

import java.util.*;

@AllArgsConstructor
@ToString
public class InMemoryRepository<ID, E extends Identifiable<ID>> {
    private final Map<ID, E> storage = new HashMap<>();

    public void save(E entity) {
        storage.put(entity.getId(), entity);
    }

    public Optional<E> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void deleteById(ID id) {
        storage.remove(id);
    }

    public List getAll() {
        return List.of(storage.values());
    }

}
