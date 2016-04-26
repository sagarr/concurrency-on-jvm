package rxjava;

/**
 * @author Sagar Rohankar
 */
public class Tweet {

    private final String text;

    public Tweet(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean hasHashTag(final String hashTag) {
        return text.contains(hashTag.substring(1));
    }

}
