package com.roni.library.contracts.service;

import com.roni.library.contracts.common.IDisposable;

/**
 * Standard interface for all modular platform services and plugins.
 */
public interface IService extends IDisposable {
    /**
     * Unique identifier for the service.
     */
    String getServiceId();

    /**
     * Initializes the service with runtime configuration.
     */
    void initialize();

    /**
     * Returns whether the service is currently running and healthy.
     */
    boolean isAvailable();
}
