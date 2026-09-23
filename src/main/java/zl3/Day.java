package zl3;

import java.util.Arrays;
import java.util.Optional;

public enum Day {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int dayNumber;

    Day(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public static Optional<Day> getDayByValue(int day) {
        return Arrays.stream(Day.values()).filter(dayNumber -> dayNumber.getNumberRepresentation() == day).findFirst();
    }

    private int getNumberRepresentation() {
        return dayNumber;
    }

    private static Day fromId(int id) {
        for (Day day : values()) {
            if (day.dayNumber == id) {
                return day;
            }
        }
        throw new IllegalArgumentException("Wrong number day: " + id);
    }

    public static String isWeekend(int getDay) {
        if (getDay == SATURDAY.dayNumber || getDay == SUNDAY.dayNumber) {
            return "Weekend";
        } else {
            return "Workday";
        }
    }

    static String getDepartureTime(int dayNumber) {
        Day day = Day.fromId(dayNumber);
        return switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "05:30";
            case SATURDAY -> "07:00";
            case SUNDAY -> "09:00";
        };
    }
}