package java.pd6.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Productt implements pd6.model.EntityInterface<String> {
    private final String id;
    private final String name;

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "'}";
    }
}
