package news.exceptions;

public class TooManyRequests extends Exception {

    public TooManyRequests(String message) {
        super(message);
    }
}
