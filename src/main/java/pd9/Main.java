package pd9;

import pd9.model.Event;
import pd9.model.EventProcessor;
import pd9.model.EventStatus;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        List<Event> events = List.of(new Event("success", EventStatus.SUCCESS, "The event went successfully."),
            new Event("fail", EventStatus.FAILED, "FAIL - check details for more info."),
            new Event("error", EventStatus.ERROR, "ERROR - something went wrong."));
        Predicate<Event> isSuccess = event -> event.getEventStatus() == EventStatus.SUCCESS;
        Predicate<Event> isFailed = event -> event.getEventStatus() == EventStatus.FAILED;
        Predicate<Event> isError = event -> event.getEventStatus() == EventStatus.ERROR;

        List<Predicate<Event>> allFilters = List.of(isSuccess, isFailed, isError);
        List<Predicate<Event>> filtersError = List.of(isFailed, isError);
        List<Predicate<Event>> filtersSuccess = List.of(isSuccess);
        Function<Event, String> formatterFull =
            event -> event.getId() + " | "
                + event.getName() + " | "
                + event.getDescription();
        Function<Event, String> formatterShort =
            event -> event.getId() + " | "
                + event.getEventStatus();
        Function<Event, String> formatterDesc = Event::getDescription;

        Consumer<String> printer = System.out::println;

        EventProcessor eventProcessor = new EventProcessor();
        System.out.println("Success full format:");
        eventProcessor.process(events, filtersSuccess, formatterFull, printer);
        System.out.println("Errors short format:");
        eventProcessor.process(events, filtersError, formatterShort, printer);
        System.out.println("All event descriptions:");
        eventProcessor.process(events, allFilters, formatterDesc, printer);
    }
}