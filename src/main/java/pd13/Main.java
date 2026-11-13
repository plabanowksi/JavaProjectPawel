package pd13;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        DateCalculator dateCalculator = new DateCalculator();

        System.out.println(dateCalculator.age(LocalDate.now()));
        System.out.println(dateCalculator.nextPayDay(LocalDate.now()));

        LocalDate from = LocalDate.of(2026, 11, 1);
        LocalDate to = LocalDate.of(2026, 11, 30);
        System.out.println(dateCalculator.businessDaysBetween(from, to));
        System.out.println(dateCalculator.formatForLocale(LocalDateTime.now(), "Europe/London"));

        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Europe/Warsaw"));
        System.out.println(dateCalculator.convertTimezone(dateTime, "Europe/London"));

        LocalDateTime date = LocalDateTime.of(2026, 11, 13, 14, 30);
        System.out.println(dateCalculator.formatForLocale(date, "Europe/Warsaw"));
        System.out.println(dateCalculator.isValidDate("31.02.2026", "dd.MM.yyyy"));
        System.out.println(dateCalculator.isValidDate("13.11.2026", "dd.MM.uuuu"));
    }
}
