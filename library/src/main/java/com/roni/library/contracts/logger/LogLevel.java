package com.roni.library.contracts.logger;

/**
 * Standard log severity levels across the Native Runtime Platform.
 */
public enum LogLevel {
    VERBOSE(2, "V"),
    DEBUG(3, "D"),
    INFO(4, "I"),
    WARN(5, "W"),
    ERROR(6, "E"),
    ASSERT(7, "A");

    private final int priority;
    private final String shortName;

    LogLevel(int priority, String shortName) {
        this.priority = priority;
        this.shortName = shortName;
    }

    public int getPriority() {
        return priority;
    }

    public String getShortName() {
        return shortName;
    }

    public boolean isLoggable(LogLevel minimumLevel) {
        return minimumLevel == null || this.priority >= minimumLevel.priority;
    }

    public static LogLevel fromString(String levelName, LogLevel defaultLevel) {
        if (levelName == null) {
            return defaultLevel;
        }
        try {
            return LogLevel.valueOf(levelName.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return defaultLevel;
        }
    }
}
