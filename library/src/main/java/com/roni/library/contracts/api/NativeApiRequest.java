package com.roni.library.contracts.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Immutable command request dispatched from Web (Bridge/REST) to Native Runtime.
 */
public final class NativeApiRequest {
    private final String requestId;
    private final String path;
    private final String action;
    private final Map<String, Object> parameters;
    private final long timestamp;

    public NativeApiRequest(String requestId, String path, String action, Map<String, Object> parameters, long timestamp) {
        this.requestId = requestId != null ? requestId : "";
        this.path = path != null ? path : "";
        this.action = action != null ? action : "";
        this.parameters = parameters != null 
                ? Collections.unmodifiableMap(new HashMap<>(parameters)) 
                : Collections.emptyMap();
        this.timestamp = timestamp > 0 ? timestamp : System.currentTimeMillis();
    }

    public String getRequestId() {
        return requestId;
    }

    public String getPath() {
        return path;
    }

    public String getAction() {
        return action;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    public String getStringParam(String key, String defaultValue) {
        Object val = parameters.get(key);
        return val != null ? String.valueOf(val) : defaultValue;
    }

    public boolean getBooleanParam(String key, boolean defaultValue) {
        Object val = parameters.get(key);
        if (val instanceof Boolean) {
            return (Boolean) val;
        } else if (val != null) {
            return Boolean.parseBoolean(String.valueOf(val));
        }
        return defaultValue;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "NativeApiRequest{requestId='" + requestId + "', path='" + path + "', action='" + action + "'}";
    }
}
