package pd6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class User implements Identifiable<Long> {
    private final Long id;
    private String name;

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
