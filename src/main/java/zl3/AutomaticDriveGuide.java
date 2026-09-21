package zl3;

import java.util.Optional;
import java.util.Scanner;

import static zl3.Days.getDayByValue;

public class AutomaticDriveGuide {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int getDay = sn.nextInt();

        String departureTime = "";

        String driveGuideDay = switch (getDay) {
            case 1, 2, 3, 4, 5 -> {
                departureTime = "05:30";
                yield "Work day";
            }
            case 6, 7 -> {
                if (getDay == 6) {
                    departureTime = "07:00";
                } else {
                    departureTime = "09:00";
                }
                yield "Weekend";
            }
            default -> "You just insterted wrong number";
        };

        Optional<Days> day = (getDayByValue(getDay));

        String finalDepartureTime = departureTime;
        day.ifPresentOrElse(
                d -> System.out.println(
                        driveGuidePlan(d.name(), finalDepartureTime, driveGuideDay)
                ),
                () -> System.out.println("You just inserted wrong number, try again")
        );

        sn.close();
    }

    private static String driveGuidePlan(String dayName, String departureTime, String driveGuideDay) {
        return String.format("Day: " + dayName + "\n" + "You picked schedule for " + driveGuideDay + "\n" + "The buss going out at " + departureTime + " am.");
    }
}
