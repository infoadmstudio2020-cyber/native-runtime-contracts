package com.roni.library.contracts;

import com.roni.library.contracts.api.NativeApiError;
import com.roni.library.contracts.api.NativeApiRequest;
import com.roni.library.contracts.api.NativeApiResponse;
import com.roni.library.contracts.common.RuntimeState;
import com.roni.library.contracts.logger.LogEntry;
import com.roni.library.contracts.logger.LogLevel;
import com.roni.library.contracts.analytics.AnalyticsEvent;
import com.roni.library.contracts.analytics.PerformanceMetric;
import com.roni.library.contracts.platform.BatteryStatusDTO;
import com.roni.library.contracts.platform.DeviceInfoDTO;
import com.roni.library.contracts.runtime.WebSourceType;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Verification unit tests for Phase 01: NativeRuntimeContracts.
 */
public class NativeRuntimeContractsTest {

    @Test
    public void testRuntimeStateLifecycle() {
        assertFalse(RuntimeState.UNINITIALIZED.isOperational());
        assertTrue(RuntimeState.READY.isOperational());
        assertTrue(RuntimeState.RUNNING.isOperational());
        assertTrue(RuntimeState.ERROR.isTerminal());
        assertTrue(RuntimeState.DESTROYED.isTerminal());
    }

    @Test
    public void testApiRequestAndResponseImmutability() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", "value");
        params.put("count", 42);

        NativeApiRequest request = new NativeApiRequest("req_101", "platform/device", "getDeviceInfo", params, System.currentTimeMillis());
        assertEquals("req_101", request.getRequestId());
        assertEquals("platform/device", request.getPath());
        assertEquals("getDeviceInfo", request.getAction());
        assertEquals("value", request.getStringParam("key", null));

        // Ensure immutability: modifying original map should not affect request parameters
        params.put("key", "modified");
        assertEquals("value", request.getStringParam("key", null));

        NativeApiResponse response = NativeApiResponse.success(request.getRequestId(), Collections.singletonMap("status", "ok"));
        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatusCode());
        assertEquals("ok", response.getData().get("status"));
    }

    @Test
    public void testLogLevelHierarchy() {
        assertTrue(LogLevel.ERROR.isLoggable(LogLevel.INFO));
        assertTrue(LogLevel.INFO.isLoggable(LogLevel.DEBUG));
        assertFalse(LogLevel.DEBUG.isLoggable(LogLevel.WARN));
        assertEquals(LogLevel.DEBUG, LogLevel.fromString("debug", LogLevel.INFO));
    }

    @Test
    public void testLogEntryCreation() {
        LogEntry entry = new LogEntry(LogLevel.WARN, "Network", "Request timed out");
        assertEquals(LogLevel.WARN, entry.getLevel());
        assertEquals("Network", entry.getTag());
        assertEquals("Request timed out", entry.getMessage());
        assertNotNull(entry.getThreadName());
    }

    @Test
    public void testAnalyticsEventAndMetric() {
        AnalyticsEvent event = new AnalyticsEvent("button_tap", Collections.singletonMap("buttonId", "submit"));
        assertEquals("button_tap", event.getName());
        assertEquals("submit", event.getParameter("buttonId"));

        PerformanceMetric metric = new PerformanceMetric("bridge_latency", 1.45, "ms");
        assertEquals("bridge_latency", metric.getMetricName());
        assertEquals(1.45, metric.getValue(), 0.001);
        assertEquals("ms", metric.getUnit());
    }

    @Test
    public void testPlatformDTOs() {
        DeviceInfoDTO device = new DeviceInfoDTO("Google", "Pixel 8", "14.0", 34, "uuid-1234", 8192);
        assertEquals("Pixel 8", device.getModel());
        assertEquals(34, device.getSdkInt());

        BatteryStatusDTO battery = new BatteryStatusDTO(85, true, "AC", 29.5f);
        assertEquals(85, battery.getLevel());
        assertTrue(battery.isCharging());
        assertEquals("AC", battery.getPluggedType());
    }

    @Test
    public void testWebSourceTypeEnum() {
        assertEquals(5, WebSourceType.values().length);
    }
}
