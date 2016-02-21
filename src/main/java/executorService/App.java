package executorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Sagar Rohankar
 */
public class App {

    public static void main(final String[] args) throws Exception {
        final ExecutorService executorService =
            new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1_000));

        final Future<Long> loadTask = executorService.submit(() -> {
            System.out.println("Loading task...");
            Thread.sleep(1000);
            return 1L;
        });

        final Future<Long> calculateTask = executorService.submit(() -> {
            System.out.println("Calculating task...");
            Thread.sleep(1000);
            return 1L;
        });

        System.out.println("Result: " + loadTask.get() + calculateTask.get());
    }
}
