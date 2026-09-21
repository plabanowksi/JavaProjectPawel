package zl3;

import java.util.Optional;

public enum Days {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    final int dayNumber;

    Days(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public static Optional<Days> getDayByValue(int day) {
        return switch (day) {
            case 1 -> Optional.of(MONDAY);
            case 2 -> Optional.of(TUESDAY);
            case 3 -> Optional.of(WEDNESDAY);
            case 4 -> Optional.of(THURSDAY);
            case 5 -> Optional.of(FRIDAY);
            case 6 -> Optional.of(SATURDAY);
            case 7 -> Optional.of(SUNDAY);
            default -> Optional.empty();
        };
    }
}