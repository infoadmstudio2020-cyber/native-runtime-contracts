package com.roni.library.contracts.api;

/**
 * Standard structured error descriptor for API requests.
 */
public final class NativeApiError {
    public static final String CODE_NOT_FOUND = "ROUTE_NOT_FOUND";
    public static final String CODE_INVALID_PARAM = "INVALID_PARAMETER";
    public static final String CODE_UNAUTHORIZED = "UNAUTHORIZED";
    public static final String CODE_EXECUTION_FAILED = "EXECUTION_FAILED";
    public static final String CODE_TIMEOUT = "TIMEOUT";

    private final String code;
    private final String message;
    private final String details;

    public NativeApiError(String code, String message, String details) {
        this.code = code != null ? code : CODE_EXECUTION_FAILED;
        this.message = message != null ? message : "Unknown error";
        this.details = details != null ? details : "";
    }

    public NativeApiError(String code, String message) {
        this(code, message, "");
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "NativeApiError{code='" + code + "', message='" + message + "'}";
    }
}
