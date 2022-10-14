package logger;

import java.time.LocalDateTime;

public record Log(Level level, LocalDateTime timestamp, String packageName, String message) {

    private static final char PIPE_SEPARATOR = '|';

    @Override
    public String toString() {
        return "[" + level + "]" + PIPE_SEPARATOR + timestamp + PIPE_SEPARATOR + packageName + PIPE_SEPARATOR + message;
    }
}
