package logger;

public enum Level {
    ERROR(4), WARNING(3), INFO(2), DEBUG(1);
    private final int level;

    Level(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

//    public static Level getLevel(String strLevel) {
//        switch (strLevel) {
//            case "INFO" -> {
//                return Level.INFO;
//            }
//            case "WARNING" -> {
//                return Level.WARNING;
//            }
//            case "ERROR" -> {
//                return Level.ERROR;
//            }
//            case "DEBUG" -> {
//                return Level.DEBUG;
//            }
//
//            default -> {
//                return null;
//            }
//        }
//    }
}
