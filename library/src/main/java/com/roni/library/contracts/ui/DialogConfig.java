package com.roni.library.contracts.ui;

/**
 * Immutable configuration DTO for native system alerts and dialogs.
 */
public final class DialogConfig {
    private final String title;
    private final String message;
    private final String positiveButtonText;
    private final String negativeButtonText;
    private final boolean cancelable;

    public DialogConfig(String title, String message, String positiveButtonText, String negativeButtonText, boolean cancelable) {
        this.title = title != null ? title : "";
        this.message = message != null ? message : "";
        this.positiveButtonText = positiveButtonText != null ? positiveButtonText : "OK";
        this.negativeButtonText = negativeButtonText;
        this.cancelable = cancelable;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public boolean isCancelable() {
        return cancelable;
    }
}
