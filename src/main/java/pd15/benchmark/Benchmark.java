package pd15.benchmark;

import java.time.Duration;
import java.time.Instant;

public class Benchmark {
    public static void main(String[] args) {

        Instant start = Instant.now();
        Instant end = Instant.now();

        long time = Duration.between(start, end).toMillis();

        System.out.println("Time: " + time + " ms");
    }
}
