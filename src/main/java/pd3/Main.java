package pd3;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Type one of options below:");
        System.out.println(Arrays.asList(MenuOperation.values()));
        Scanner sn = new Scanner(System.in);
        String menuInput = sn.nextLine();

        Optional<MenuOperation> menu = MenuOperation.getOptionbyValue(menuInput);
        if (menu.isPresent()) {
            MenuOperation operation = menu.orElseThrow();
            MathLibrary.menuMathLibrary(operation, sn);
        } else {
            throw new IllegalArgumentException("Unexpected value: " + menuInput);
        }
        sn.close();
    }

}