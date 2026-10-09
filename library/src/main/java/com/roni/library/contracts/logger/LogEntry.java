package com.roni.library.contracts.logger;

/**
 * Encapsulates an individual immutable structured log entry.
 */
public final class LogEntry {
    private final long timestamp;
    private final LogLevel level;
    private final String tag;
    private final String message;
    private final String threadName;
    private final Throwable throwable;
    private final String correlationId;

    public LogEntry(long timestamp, LogLevel level, String tag, String message, String threadName, Throwable throwable, String correlationId) {
        this.timestamp = timestamp > 0 ? timestamp : System.currentTimeMillis();
        this.level = level != null ? level : LogLevel.INFO;
        this.tag = tag != null ? tag : "NativeRuntime";
        this.message = message != null ? message : "";
        this.threadName = threadName != null ? threadName : Thread.currentThread().getName();
        this.throwable = throwable;
        this.correlationId = correlationId != null ? correlationId : "";
    }

    public LogEntry(LogLevel level, String tag, String message) {
        this(System.currentTimeMillis(), level, tag, message, Thread.currentThread().getName(), null, "");
    }

    public long getTimestamp() {
        return timestamp;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getTag() {
        return tag;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return threadName;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    @Override
    public String toString() {
        return "[" + level.getShortName() + "/" + tag + " @" + threadName + "] " + message;
    }
}
