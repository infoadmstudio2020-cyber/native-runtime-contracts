package com.roni.library.contracts.theme;

/**
 * Standard theme configuration contract interface.
 */
public interface IThemeConfig {
    /**
     * Enum representing system or custom theme modes.
     */
    enum ThemeMode {
        LIGHT,
        DARK,
        SYSTEM
    }

    ThemeMode getThemeMode();
    int getPrimaryColor();
    int getStatusBarColor();
    int getNavigationBarColor();
    boolean isStatusBarIconsLight();
    boolean isNavigationBarIconsLight();
}
