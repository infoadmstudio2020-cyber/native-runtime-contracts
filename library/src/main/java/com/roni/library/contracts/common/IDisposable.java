package com.roni.library.contracts.common;

/**
 * Standard interface for resources and subsystems that require deterministic cleanup.
 */
public interface IDisposable {
    /**
     * Releases all held resources, cancels background execution, and disposes state.
     */
    void dispose();

    /**
     * Returns whether the resource has already been disposed.
     *
     * @return true if disposed, false otherwise.
     */
    boolean isDisposed();
}
