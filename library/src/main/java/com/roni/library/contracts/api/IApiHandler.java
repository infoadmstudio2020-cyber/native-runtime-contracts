package com.roni.library.contracts.api;

/**
 * Functional contract interface for handling routed API commands.
 */
public interface IApiHandler {
    /**
     * Returns the route prefix handled by this handler (e.g. "ui/", "logger/", "platform/").
     *
     * @return the unique route prefix.
     */
    String getRoutePrefix();

    /**
     * Executes the given API request asynchronously.
     *
     * @param request  the incoming API request DTO.
     * @param callback the asynchronous callback to report results.
     */
    void handle(NativeApiRequest request, IApiCallback callback);
}
