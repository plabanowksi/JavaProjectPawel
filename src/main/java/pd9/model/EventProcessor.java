package pd9.model;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {

    public void process(List<Event> events, List<Predicate<Event>> filters,
                        Function<Event, String> formatter, Consumer<String> output) {
        for (Event event : events) {
            boolean matchesAll = true;
            for (Predicate<Event> filter : filters) {
                if (!filter.test(event)) {
                    matchesAll = false;
                }
            }
            if (matchesAll) {
                output.accept(formatter.apply(event));
            }
        }
    }
}