package com.roni.library.contracts.platform;

/**
 * Immutable Data Transfer Object representing device battery status.
 */
public final class BatteryStatusDTO {
    private final int level;
    private final boolean isCharging;
    private final String pluggedType;
    private final float temperature;

    public BatteryStatusDTO(int level, boolean isCharging, String pluggedType, float temperature) {
        this.level = level;
        this.isCharging = isCharging;
        this.pluggedType = pluggedType != null ? pluggedType : "UNPLUGGED";
        this.temperature = temperature;
    }

    public int getLevel() {
        return level;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public String getPluggedType() {
        return pluggedType;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "BatteryStatusDTO{level=" + level + "%, charging=" + isCharging + ", plugged='" + pluggedType + "'}";
    }
}
