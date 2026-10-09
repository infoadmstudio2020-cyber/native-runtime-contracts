package com.roni.library.contracts.analytics;

/**
 * Encapsulates performance telemetry metrics such as Bridge latency, Web Vitals, and FPS.
 */
public final class PerformanceMetric {
    private final String metricName;
    private final double value;
    private final String unit;
    private final long timestamp;

    public PerformanceMetric(String metricName, double value, String unit, long timestamp) {
        this.metricName = metricName != null ? metricName : "";
        this.value = value;
        this.unit = unit != null ? unit : "ms";
        this.timestamp = timestamp > 0 ? timestamp : System.currentTimeMillis();
    }

    public PerformanceMetric(String metricName, double value, String unit) {
        this(metricName, value, unit, System.currentTimeMillis());
    }

    public PerformanceMetric(String metricName, double value) {
        this(metricName, value, "ms", System.currentTimeMillis());
    }

    public String getMetricName() {
        return metricName;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "PerformanceMetric{" + metricName + "=" + value + " " + unit + "}";
    }
}
