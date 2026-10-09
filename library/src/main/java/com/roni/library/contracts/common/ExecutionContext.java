package com.roni.library.contracts.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Immutable execution context provided to runtime components and handlers.
 */
public final class ExecutionContext {
    private final String sessionId;
    private final String correlationId;
    private final long timestamp;
    private final Map<String, Object> attributes;

    public ExecutionContext(String sessionId, String correlationId, long timestamp, Map<String, Object> attributes) {
        this.sessionId = sessionId != null ? sessionId : "";
        this.correlationId = correlationId != null ? correlationId : "";
        this.timestamp = timestamp > 0 ? timestamp : System.currentTimeMillis();
        this.attributes = attributes != null 
                ? Collections.unmodifiableMap(new HashMap<>(attributes)) 
                : Collections.emptyMap();
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }
}
