package rxjava;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @author Sagar Rohankar
 */
public class App {

    public static void main(final String[] args) throws InterruptedException {
        final Observable<Tweet> tweets = Observable.just(new Tweet("Hey rxjava"), new Tweet("Hello world from #java"));

        final Observable<List<String>> batches = tweets.filter(t -> t.hasHashTag("#java")) //
            .sample(100, TimeUnit.MILLISECONDS) //
            .map(Tweet::getText) //
            .buffer(10); //

        batches.subscribe(arg -> System.err.println(arg));
    }
}
