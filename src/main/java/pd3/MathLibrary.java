package pd3;

import java.util.Arrays;
import java.util.Scanner;

public class MathLibrary {

    private static long factorialRec(int n) {
        if (n > 0) {
            return n + factorialRec(n - 1);
        } else {
            return 0;
        }
    }

    private static long factorialIter(int n) {
        int res = 1, i;
        for (i = 2; i <= n; i++)
            res *= i;

        return res;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;

    }

    private static int[] sieveOfEratosthenes(int limit) {
        boolean[] numbers = new boolean[limit + 1];

        for (int i = 2; i <= Math.sqrt(limit); i++) {
            if (!numbers[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    numbers[j] = true;
                }
            }
        }

        int count = 0;

        for (int i = 2; i <= limit; i++) {
            if (!numbers[i]) {
                count++;
            }
        }


        int[] result = new int[count];
        int index = 0;
        for (int i = 2; i <= limit; i++) {
            if (!numbers[i]) {
                result[index] = i;
                index++;
            }
        }
        return result;
    }

    private static int gcd(int a, int b) {
        if (a == b) {
            return 0;
        } else {
            do {
                int temp = b;
                b = a % b;
                a = temp;

            } while (b != 0);
        }
        return a;
    }

    private static double power(double base, int exp) {
        if (exp == 0) {
            return 1;
        } else if (exp % 2 == 0) {
            double moc = power(base, exp / 2);
            return moc * moc;
        } else {
            return base * power(base, exp - 1);
        }
    }

    private static int benchmarkForFactorialRec() {
        System.out.println("Benchmark for Factorial Rec");
        long startTime = System.currentTimeMillis();
        System.out.println(factorialRec(20));
        long endTime = System.currentTimeMillis();
        return Math.toIntExact(endTime - startTime);
    }

    private static int benchmarkForFactorialIter() {
        System.out.println("Benchmark for FactorialIter");
        long startTime2 = System.currentTimeMillis();
        System.out.println(factorialIter(20));
        long endTime2 = System.currentTimeMillis();
        return Math.toIntExact(endTime2 - startTime2);

    }

    private static void bonus(int length) {
        int width = 50;
        String text = "*";

        for (int i = 1; i < length * 2; i += 2) {
            String input = text.repeat(i);
            int padding = (width - input.length()) / 2;

            String centeredString = String.format("%" + (padding + input.length()) + "s", input);
            System.out.println(centeredString);
        }
    }

    public static void menuMathLibrary(MenuOperation operation, Scanner sn) {
        switch (operation) {
            case FACTORIALREC -> {
                System.out.println("Insert input:");
                int input = sn.nextInt();
                System.out.println(factorialRec(input));
            }
            case FACTORIALITER -> {
                System.out.println("Insert input:");
                int input = sn.nextInt();
                System.out.println(factorialIter(input));
            }
            case PRIME -> {
                System.out.println("Insert input:");
                int input = sn.nextInt();
                System.out.println(isPrime(input));
            }
            case SIEVEOFERATOSTHENES -> {
                System.out.println("Insert input:");
                int input = sn.nextInt();
                System.out.println(Arrays.toString(sieveOfEratosthenes(input)));
            }
            case BENCHMARKFACTORIALREC -> System.out.println(benchmarkForFactorialRec());

            case BENCHMARKFACTORIALITER -> System.out.println(benchmarkForFactorialIter());

            case GCD -> {
                System.out.println("Insert first input:");
                int inputA = sn.nextInt();
                System.out.println("Insert second input:");
                int inputB = sn.nextInt();
                System.out.println(gcd(inputA, inputB));
            }
            case POWER -> {
                System.out.println("Insert first input: (double)");
                double inputA = sn.nextDouble();
                System.out.println("Insert second input:");
                int inputB = sn.nextInt();
                System.out.println(power(inputA, inputB));
            }
            case BONUS -> {
                System.out.println("Insert how many rows do you want:");
                int input = sn.nextInt();
                bonus(input);
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + operation);
        }
    }
}