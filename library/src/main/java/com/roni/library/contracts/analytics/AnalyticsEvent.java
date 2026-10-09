package com.roni.library.contracts.analytics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Immutable analytics telemetry event model.
 */
public final class AnalyticsEvent {
    private final String name;
    private final Map<String, Object> parameters;
    private final long timestamp;

    public AnalyticsEvent(String name, Map<String, Object> parameters, long timestamp) {
        this.name = name != null ? name : "unknown_event";
        this.parameters = parameters != null 
                ? Collections.unmodifiableMap(new HashMap<>(parameters)) 
                : Collections.emptyMap();
        this.timestamp = timestamp > 0 ? timestamp : System.currentTimeMillis();
    }

    public AnalyticsEvent(String name, Map<String, Object> parameters) {
        this(name, parameters, System.currentTimeMillis());
    }

    public AnalyticsEvent(String name) {
        this(name, Collections.emptyMap(), System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "AnalyticsEvent{name='" + name + "', params=" + parameters.size() + ", time=" + timestamp + "}";
    }
}
