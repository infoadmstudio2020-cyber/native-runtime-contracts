package com.roni.library.contracts.ui;

import java.util.Collections;
import java.util.List;

/**
 * Immutable configuration DTO for native toolbar control.
 */
public final class ToolbarConfig {
    public static final class ActionItem {
        private final String id;
        private final String title;
        private final String iconRes;

        public ActionItem(String id, String title, String iconRes) {
            this.id = id != null ? id : "";
            this.title = title != null ? title : "";
            this.iconRes = iconRes != null ? iconRes : "";
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getIconRes() {
            return iconRes;
        }
    }

    private final boolean visible;
    private final String title;
    private final String subtitle;
    private final String backgroundColor;
    private final String titleColor;
    private final List<ActionItem> actions;

    public ToolbarConfig(boolean visible, String title, String subtitle, String backgroundColor, String titleColor, List<ActionItem> actions) {
        this.visible = visible;
        this.title = title != null ? title : "";
        this.subtitle = subtitle != null ? subtitle : "";
        this.backgroundColor = backgroundColor;
        this.titleColor = titleColor;
        this.actions = actions != null ? Collections.unmodifiableList(actions) : Collections.emptyList();
    }

    public boolean isVisible() {
        return visible;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public List<ActionItem> getActions() {
        return actions;
    }
}
