package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.function.*;

public class DefaultLogger implements Logger {

    private static final String LOG_FORMAT = "[%s]|%s|%s|%s";
    private static final String FILE_NAME = "log-";
    private static final String FILE_NAME_EXTENSION = ".txt";

    private static int logCnt = 0;
    private final LoggerOptions options;
    private Path activeFile;

    private BufferedWriter writer;

    public DefaultLogger(LoggerOptions options) {
        this.options = options;
        this.activeFile = generateLogFilePath();
        try {
            openLogFile();
        } catch (IOException e) {
            if (options.shouldThrowErrors()) {
                throw new LogException("failed to log: " + e.getMessage());
            }
        }
    }

    @Override
    public void log(Level level, LocalDateTime timestamp, String message) {
        if (level == null || timestamp == null || message == null || message.isBlank()) {
            throw new IllegalArgumentException("Your log message can't be logged, because some part of your log is invalid");
        }

        if (level.getLevel() < options.getMinLogLevel().getLevel()) {
            return;
        }

        try {

            long bytesInTheMessage = level.toString().getBytes().length + timestamp.toString().getBytes().length + this.getClass().getPackageName().getBytes().length + message.getBytes().length;

            if (Files.size(activeFile) + bytesInTheMessage >= options.getMaxFileSizeBytes()) {
                closeLogFile();
                activeFile = generateLogFilePath();
                openLogFile();

            }
            String logMessage =
                    String.format(LOG_FORMAT, level.name(), timestamp, options.getClazz().getPackageName(), message);

            writeToActiveLogFile(logMessage);

        } catch (IOException e) {
            if (options.shouldThrowErrors()) {
                throw new LogException("failed to log: " + e.getMessage());
            }
        }
    }

    @Override
    public LoggerOptions getOptions() {
        return this.options;
    }

    @Override
    public Path getCurrentFilePath() {
        return activeFile;
    }

    private Path generateLogFilePath() {
        return Path.of(options.getDirectory() + File.separator + FILE_NAME + logCnt++ + FILE_NAME_EXTENSION);
    }

    private void openLogFile() throws IOException {
        writer = Files.newBufferedWriter(activeFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void closeLogFile() throws IOException {
        writer.close();
    }

    private void writeToActiveLogFile(String logMessage) throws IOException{
        writer.write(logMessage + System.lineSeparator());
        writer.flush();
    }
}
