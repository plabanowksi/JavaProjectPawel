package zl3;

import java.util.Optional;
import java.util.Scanner;

import static zl3.Day.*;

public class AutomaticDriveGuide {
    public static void main(String[] args) {
        System.out.println("Pick number 1-7 to check Drive Guide");
        Scanner sn = new Scanner(System.in);
        int dayInput = sn.nextInt();

        Optional<Day> day = getDayByValue(dayInput);
        day.ifPresentOrElse(
                d -> System.out.println(
                        getDriveGuicePlan(d.name(), d.getDepartureTime(), d.isWeekend())),
                () -> System.out.println("You just inserted wrong number, try again")
        );

        sn.close();
    }

    private static String getDriveGuicePlan(String dayName, String departureTime, String isWeekend) {
        return String.format("Day: %s \n You picked schedule for %s \n The buss going out at %s am.", dayName,isWeekend,departureTime);
    }
}