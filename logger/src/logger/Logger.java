package logger;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

public interface Logger {

    void log(Level level, LocalDateTime timestamp, String message) throws IOException;

    LoggerOptions getOptions();

    Path getCurrentFilePath();
}
