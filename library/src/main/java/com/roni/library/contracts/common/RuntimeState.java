package com.roni.library.contracts.common;

/**
 * Represents the lifecycle execution state of the Native Runtime Platform.
 */
public enum RuntimeState {
    /**
     * Platform has been constructed but not yet initialized.
     */
    UNINITIALIZED,

    /**
     * Subsystems, logging, and services are actively initializing.
     */
    INITIALIZING,

    /**
     * Initialization has completed successfully and all subsystems are idle and ready.
     */
    READY,

    /**
     * Web application is loaded and running in the foreground.
     */
    RUNNING,

    /**
     * Platform execution is suspended or paused in the background.
     */
    PAUSED,

    /**
     * A non-recoverable error occurred during lifecycle execution.
     */
    ERROR,

    /**
     * Platform resources have been completely released and destroyed.
     */
    DESTROYED;

    public boolean isTerminal() {
        return this == ERROR || this == DESTROYED;
    }

    public boolean isOperational() {
        return this == READY || this == RUNNING;
    }
}
