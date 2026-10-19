package pd6.entity;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class User implements EntityInterface<Long>, pd6.entity.EntityInterface<Long> {
    private final long id;
    private String name;
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
