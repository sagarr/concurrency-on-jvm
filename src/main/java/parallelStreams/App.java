package parallelStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sagar Rohankar
 */
public class App {

    public static void main(final String[] args) {
        final List<String> list = new ArrayList<>(Arrays.asList("sdas", "das", "asd", "gry", "gtd", "wqe", "gse", "hkop"));

        System.out.println(list.parallelStream() //
            .filter(s -> s.startsWith("w")) //
            .findAny());
    }

}
