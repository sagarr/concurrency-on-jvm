package forkJoinPool;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Sagar Rohankar
 */
public class App {

    public static void main(final String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        final List<Path> dirs = forkJoinPool.invoke(new RecursiveSearchTask(Paths.get("D:\\github")));
        System.out.println("Dirs....");
        dirs.forEach(System.out::println);
    }

}
