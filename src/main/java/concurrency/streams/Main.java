package concurrency.streams;

import java.util.Arrays;
import java.util.Random;

/**
 * To boost the performance of a streamline operations, a Parallel Stream is used.
 * Under the ForkJoinPool is used.
 * <p>
 * NB! Why a parallel stream may not always be faster than a serial stream !
 * - Parallel streams introduce some overhead, such as the need to create and manage multiple threads.
 * This overhead can be significant for small arrays.
 * - Parallel streams need to synchronize their operations to ensure that the results are correct.
 * This synchronization can also add overhead, especially for small arrays.
 */
public class Main {

    public static void main(String[] args) {
        int numbersLength = 100_000_000;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();
        int delta = 0;
        int iterations = 25;

        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            double averageSerial = Arrays.stream(numbers).average().orElseThrow();
            long elapsedSerial = System.nanoTime() - start;
            // System.out.printf("Ave = %.2f , elapsed = %d nanos or %.2f ms%n", averageSerial, elapsedSerial, elapsedSerial / 1000000.0);

            start = System.nanoTime();
            double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
            long elapsedParallel = System.nanoTime() - start;
            // System.out.printf("Ave = %.2f , elapsed = %d nanos or %.2f ms%n", averageParallel, elapsedParallel, elapsedParallel / 1000000.0);
            delta += (elapsedSerial - elapsedParallel);
        }

        System.out.printf("Parallel is [%d] nanos or [%.2f] ms faster on average %n",
                delta / iterations, delta / 1000000.0 / iterations);
    }

}
