package pd3;

import java.util.Arrays;
import java.util.Optional;

public enum MenuOperation {
    FACTORIALREC,
    FACTORIALITER,
    PRIME,
    SIEVEOFERATOSTHENES,
    GCD,
    POWER,
    BENCHMARKFACTORIALREC,
    BENCHMARKFACTORIALITER,
    BONUS;


    public static Optional<MenuOperation> getOptionbyValue(String menuInput) {
        return Arrays.stream(MenuOperation.values())
                .filter(menu -> menu.name().equalsIgnoreCase(menuInput))
                .findFirst();
    }
}