package forkJoinPool;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.StreamSupport;

/**
 * @author Sagar Rohankar
 */
public class RecursiveSearchTask extends RecursiveTask<List<Path>> {

    private static final long serialVersionUID = 1L;
    private final Path path;

    public RecursiveSearchTask(final Path path) {
        this.path = path;
    }

    @Override
    protected List<Path> compute() {
        try (final DirectoryStream<Path> children = Files.newDirectoryStream(path, Files::isDirectory)) {
            final List<ForkJoinTask<List<Path>>> subTasks = StreamSupport //
                .stream(children.spliterator(), false) //
                .map(child -> new RecursiveSearchTask(child).fork()) //
                .collect(toList()); //

            final List<Path> desendents = subTasks.stream() //
                .flatMap(task -> task.join().stream()) //
                .collect(toList()); //

            final List<Path> dirList = new ArrayList<Path>(desendents);
            dirList.add(path);
            return dirList;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
