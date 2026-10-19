package pd6.repository;

import lombok.AllArgsConstructor;
import lombok.ToString;
import pd6.entity.EntityInterface;

import util.Collection;
import util.HashMap;
import util.Map;
import util.Optional;

@AllArgsConstructor
@ToString
public class EntityStorage<ID, E extends EntityInterface<ID>> {
    private final Map<ID, E> storage = new HashMap<>();

    public void save(E entity) {
        storage.put(entity.getId(), entity);
    }

    public Optional<E> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void delete(ID id) {
        storage.remove(id);
    }

    public Collection<E> getAll() {
        return storage.values();
    }

}
