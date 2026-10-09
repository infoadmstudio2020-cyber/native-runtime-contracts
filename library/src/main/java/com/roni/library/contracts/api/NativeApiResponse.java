package com.roni.library.contracts.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Immutable response returned to the caller (Web Bridge or REST).
 */
public final class NativeApiResponse {
    private final String requestId;
    private final int statusCode;
    private final boolean success;
    private final Map<String, Object> data;
    private final NativeApiError error;
    private final long timestamp;

    private NativeApiResponse(String requestId, int statusCode, boolean success, Map<String, Object> data, NativeApiError error) {
        this.requestId = requestId != null ? requestId : "";
        this.statusCode = statusCode;
        this.success = success;
        this.data = data != null ? Collections.unmodifiableMap(new HashMap<>(data)) : Collections.emptyMap();
        this.error = error;
        this.timestamp = System.currentTimeMillis();
    }

    public static NativeApiResponse success(String requestId, Map<String, Object> data) {
        return new NativeApiResponse(requestId, 200, true, data, null);
    }

    public static NativeApiResponse success(String requestId) {
        return new NativeApiResponse(requestId, 200, true, Collections.emptyMap(), null);
    }

    public static NativeApiResponse error(String requestId, int statusCode, NativeApiError error) {
        return new NativeApiResponse(requestId, statusCode, false, Collections.emptyMap(), error);
    }

    public static NativeApiResponse error(String requestId, NativeApiError error) {
        return new NativeApiResponse(requestId, 500, false, Collections.emptyMap(), error);
    }

    public String getRequestId() {
        return requestId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public NativeApiError getError() {
        return error;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
