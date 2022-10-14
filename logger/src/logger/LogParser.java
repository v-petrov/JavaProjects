package logger;

import java.time.LocalDateTime;
import java.util.List;

public interface LogParser {

    List<Log> getLogs(Level level);

    List<Log> getLogs(LocalDateTime form, LocalDateTime to);

    List<Log> getLogsTail(int n);
}
