package zl3;

import java.util.Optional;
import java.util.Scanner;

import static zl3.Day.*;

public class AutomaticDriveGuide {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int getDay = sn.nextInt();

        Optional<Day> day = (getDayByValue(getDay));
        String isWeekend = isWeekend(getDay);
        String departureTime = getDepartureTime(getDay);


        day.ifPresentOrElse(
                d -> System.out.println(
                        driveGuidePlan(d.name(), departureTime, isWeekend)
                ),
                () -> System.out.println("You just inserted wrong number, try again")
        );

        sn.close();
    }

    private static String driveGuidePlan(String dayName, String departureTime, String isWeekend) {
        return String.format("Day: " + dayName + "\n" + "You picked schedule for " + isWeekend +
                "\n" + "The buss going out at " + departureTime + " am.");
    }
}