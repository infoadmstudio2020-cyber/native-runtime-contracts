package com.roni.library.contracts.analytics;

/**
 * Standard contract interface for analytics backends and telemetry providers.
 */
public interface IAnalyticsProvider {
    /**
     * Initializes the provider with target configuration.
     */
    void initialize();

    /**
     * Records a user or system interaction event.
     *
     * @param event the structured analytics event.
     */
    void trackEvent(AnalyticsEvent event);

    /**
     * Records a numeric performance or latency metric.
     *
     * @param metric the performance metric DTO.
     */
    void recordMetric(PerformanceMetric metric);

    /**
     * Flushes any in-memory queued telemetry events to persistent storage or remote backend.
     */
    void flush();
}
