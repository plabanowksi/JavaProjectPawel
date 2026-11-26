package pd13;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;

public class DateCalculator {
    public int age(LocalDate birthDate) {
        return birthDate.getYear();
    }

    public static LocalDate nextPayDay(LocalDate from) {
        LocalDate date = from.with(TemporalAdjusters.lastDayOfMonth());
        while (date.getDayOfWeek().getValue() > 5) {
            date = date.minusDays(1);
        }
        return date;
    }

    public static long daysUntil(LocalDate from, LocalDate to) {
        long workingDays = 0L;
        if (!from.isAfter(to)){
            while (!from.isAfter(to)) {
                if (from.getDayOfWeek().getValue() <= 5) {
                    workingDays++;
                }
                from = from.plusDays(1);
            }
        return workingDays;
        }else {
            throw new IllegalArgumentException("To can't be less, than FROM");
        }
    }

    public static ZonedDateTime convertTimezone(ZonedDateTime dt, String targetZone) {
        return dt.withZoneSameInstant(ZoneId.of(targetZone));
    }

    public static String formatForLocale(LocalDateTime dt, String languageTag) {
        ZoneId zone = ZoneId.of(languageTag);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        return dt.atZone(zone).format(formatter);
    }

    public static boolean isValidDate(String input, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

