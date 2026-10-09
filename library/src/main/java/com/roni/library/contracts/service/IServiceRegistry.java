package com.roni.library.contracts.service;

/**
 * Contract interface for the centralized service registry.
 */
public interface IServiceRegistry {
    /**
     * Registers a service instance.
     */
    <T extends IService> void registerService(Class<T> serviceClass, T serviceInstance);

    /**
     * Retrieves a registered service by its contract type.
     */
    <T extends IService> T getService(Class<T> serviceClass);

    /**
     * Unregisters and disposes a service.
     */
    <T extends IService> boolean unregisterService(Class<T> serviceClass);

    /**
     * Returns true if a service of the specified class is registered.
     */
    boolean hasService(Class<? extends IService> serviceClass);
}
