package com.roni.library.contracts.logger;

/**
 * Standard structured logging contract interface.
 */
public interface ILogger {
    void v(String tag, String message);
    void d(String tag, String message);
    void i(String tag, String message);
    void w(String tag, String message);
    void w(String tag, String message, Throwable throwable);
    void e(String tag, String message);
    void e(String tag, String message, Throwable throwable);

    void log(LogEntry entry);

    void setMinimumLevel(LogLevel level);
    LogLevel getMinimumLevel();
}
