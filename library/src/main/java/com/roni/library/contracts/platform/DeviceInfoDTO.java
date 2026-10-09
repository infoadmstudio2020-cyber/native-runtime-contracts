package com.roni.library.contracts.platform;

/**
 * Immutable Data Transfer Object representing hardware device specifications.
 */
public final class DeviceInfoDTO {
    private final String manufacturer;
    private final String model;
    private final String osVersion;
    private final int sdkInt;
    private final String deviceUuid;
    private final long totalMemory;

    public DeviceInfoDTO(String manufacturer, String model, String osVersion, int sdkInt, String deviceUuid, long totalMemory) {
        this.manufacturer = manufacturer != null ? manufacturer : "Unknown";
        this.model = model != null ? model : "Unknown";
        this.osVersion = osVersion != null ? osVersion : "Unknown";
        this.sdkInt = sdkInt;
        this.deviceUuid = deviceUuid != null ? deviceUuid : "";
        this.totalMemory = totalMemory;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public int getSdkInt() {
        return sdkInt;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    @Override
    public String toString() {
        return "DeviceInfoDTO{model='" + model + "', os='" + osVersion + "', sdk=" + sdkInt + "}";
    }
}
