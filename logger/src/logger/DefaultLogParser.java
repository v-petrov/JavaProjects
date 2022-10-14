package logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DefaultLogParser implements LogParser {

    private final Path logsFilePath;


    public DefaultLogParser(Path logsFilePath) {
        this.logsFilePath = logsFilePath;
    }

    @Override
    public List<Log> getLogs(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("The level of the log is null");
        }

        List<Log> levelLogs = new LinkedList<>();
        List<Log> allLogs = getAllLogsInTheCurrentFile();

        for (Log log : allLogs) {
            if (log.level() == level) {
                levelLogs.add(log);
            }
        }

        return levelLogs;
    }

    @Override
    public List<Log> getLogs(LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Null time");
        }

        List<Log> timeLogs = new LinkedList<>();
        List<Log> allLogs = getAllLogsInTheCurrentFile();

        for (Log log : allLogs) {
            if (log.timestamp().isAfter(from) && (log.timestamp().isBefore(to))) {
                timeLogs.add(log);
            }
        }

        return timeLogs;
    }

    @Override
    public List<Log> getLogsTail(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n cannot be a negative number");
        }

        List<Log> allLogs = getAllLogsInTheCurrentFile();

        return allLogs.subList(Math.max(allLogs.size() - n, 0), allLogs.size());
    }

    private List<Log> getAllLogsInTheCurrentFile() {
        List<Log> allLogs = new LinkedList<>();

        try (var reader = new BufferedReader(Files.newBufferedReader(logsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Log log = getALog(line);
                allLogs.add(log);
            }

        } catch (IOException e) {
            throw new LogException("Can't read from the file");
        }

        return allLogs;
    }

    private Log getALog(String line) {
        String[] elementsOfLine = line.split("\\|");

        for (int i = 0; i < elementsOfLine.length; i++) {
            elementsOfLine[i] = elementsOfLine[i].trim();
        }

        Level level = Level.valueOf(elementsOfLine[0].substring(1, elementsOfLine[0].length() - 1));
        LocalDateTime timestamp = LocalDateTime.parse(elementsOfLine[1], DateTimeFormatter.ISO_DATE_TIME);
        String packageName = elementsOfLine[2];
        String message = elementsOfLine[3];

        return new Log(level, timestamp, packageName, message);
    }
//
//    public static NoPainNoGain test() {
//        return () -> 1;
//    }
//
//    public static void test1(NoPainNoGain npng) {
//        System.out.println(npng.pain());
//    }
//
//    public static<K> void increment(Map<K, Integer> map, K key) {
//        map.merge(key, 10, Integer::sum);
//    }
//
//    public static void main(String[] args) {
////        NoPainNoGain npng = DefaultLogParser::test;
////        test1(() -> 1);
////
////        NoPainNoGain npng1 = test();
////        int n = npng1.pain();
////        System.out.println(n);
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("A", 1);
//
//        increment(map, "A");
//        increment(map, "A");
//
//        System.out.println(map);
//    }
//
//    @FunctionalInterface
//    interface NoPainNoGain {
//        int pain();
//    }
}
