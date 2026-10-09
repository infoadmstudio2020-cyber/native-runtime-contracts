package com.roni.library.contracts.api;

/**
 * Asynchronous callback contract for API command execution.
 */
public interface IApiCallback {
    /**
     * Invoked when the API request completes successfully.
     *
     * @param response the success response object.
     */
    void onSuccess(NativeApiResponse response);

    /**
     * Invoked when the API request fails.
     *
     * @param error the structured error details.
     */
    void onError(NativeApiError error);
}
