package pd6.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Product implements Identifiable<String> {
    private final String id;
    private final String name;

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "'}";
    }
}
