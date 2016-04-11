package completableFuture;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Sagar Rohankar
 */
public class App {

    public static void main(final String[] args) {
        final Instant startTime = Instant.now();
        final Executor executor = Executors.newFixedThreadPool(5);
        final CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> loadUser("sr@live.com"), executor);
        final CompletableFuture<Location> locationFuture = CompletableFuture.supplyAsync(() -> trackDevice());

        final CompletableFuture<Recommendation> recommendationFuture =
            userFuture.thenCombine(locationFuture, (user, loc) -> buildRecommendation(user, loc));

        final CompletableFuture<String> bestFuture =
            recommendationFuture.thenCompose((final Recommendation r) -> guessBest(r));
        while (!bestFuture.isDone()) {
        }
        System.err.println(bestFuture + " time taken to find best: "
            + Instant.now().minusMillis(startTime.toEpochMilli()).toEpochMilli() + " ms");
    }

    private static Recommendation buildRecommendation(final User user, final Location loc) {
        System.out.println("Getting recommendations...");
        sleep();
        return new Recommendation();
    }

    private static Location trackDevice() {
        System.out.println("Tracking device...");
        sleep();
        return new Location(3.4534343, 45.3242432);
    }

    private static User loadUser(final String id) {
        System.out.println("Loading user...");
        sleep();
        return new User(id);
    }

    private static CompletableFuture<String> guessBest(final Recommendation r) {
        System.out.println("Guessing best recommendation...");
        sleep();
        return CompletableFuture.supplyAsync(() -> "best!!!");
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
        }
    }
}
