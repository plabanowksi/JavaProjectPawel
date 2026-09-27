package pd3;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    FACTORIALREC,
    FACTORIALITER,
    PRIME,
    SIEVEOFERATOSTHENES,
    GCD,
    POWER,
    BENCHMARKFACTORIALREC,
    BENCHMARKFACTORIALITER,
    BONUS;


    public static Optional<Menu> getMenuByValue(String menuInput) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().equalsIgnoreCase(menuInput))
                .findFirst();
    }
}