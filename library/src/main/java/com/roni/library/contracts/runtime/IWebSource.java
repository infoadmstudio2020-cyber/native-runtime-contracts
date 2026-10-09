package com.roni.library.contracts.runtime;

/**
 * Contract interface representing a resolved web application bundle source.
 */
public interface IWebSource {
    /**
     * Returns the type of web source.
     */
    WebSourceType getSourceType();

    /**
     * Returns the local file path or asset folder descriptor.
     */
    String getPath();

    /**
     * Returns the entry point relative URL (e.g., "index.html").
     */
    String getEntryPoint();

    /**
     * Returns whether the source requires on-the-fly decryption or unpacking.
     */
    boolean requiresPreProcessing();
}
