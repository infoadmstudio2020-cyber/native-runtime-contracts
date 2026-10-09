package com.roni.library.contracts;

import com.roni.library.contracts.common.*;
import com.roni.library.contracts.api.*;
import com.roni.library.contracts.logger.*;
import com.roni.library.contracts.analytics.*;
import com.roni.library.contracts.service.*;
import com.roni.library.contracts.platform.*;
import com.roni.library.contracts.theme.*;
import com.roni.library.contracts.ui.*;
import com.roni.library.contracts.runtime.*;

import java.util.*;

public class ContractsVerificationRunner {
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    private static void assertTrue(String testName, boolean condition) {
        if (condition) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.println("  [FAIL] " + testName);
            testsFailed++;
        }
    }

    private static void assertEquals(String testName, Object expected, Object actual) {
        boolean match = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        if (match) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.println("  [FAIL] " + testName + " - Expected: " + expected + ", Actual: " + actual);
            testsFailed++;
        }
    }

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("NativeRuntimeContracts (Phase 01) Verification");
        System.out.println("=================================================");

        // Test 1: RuntimeState lifecycle
        assertTrue("RuntimeState UNINITIALIZED is not operational", !RuntimeState.UNINITIALIZED.isOperational());
        assertTrue("RuntimeState READY is operational", RuntimeState.READY.isOperational());
        assertTrue("RuntimeState RUNNING is operational", RuntimeState.RUNNING.isOperational());
        assertTrue("RuntimeState ERROR is terminal", RuntimeState.ERROR.isTerminal());
        assertTrue("RuntimeState DESTROYED is terminal", RuntimeState.DESTROYED.isTerminal());

        // Test 2: ExecutionContext immutability
        Map<String, Object> ctxAttrs = new HashMap<>();
        ctxAttrs.put("env", "production");
        ExecutionContext ctx = new ExecutionContext("sess_1", "corr_1", 1000L, ctxAttrs);
        assertEquals("ExecutionContext sessionId", "sess_1", ctx.getSessionId());
        assertEquals("ExecutionContext attribute", "production", ctx.getAttribute("env"));
        ctxAttrs.put("env", "tampered");
        assertEquals("ExecutionContext attribute immutability", "production", ctx.getAttribute("env"));

        // Test 3: NativeApiRequest & NativeApiResponse
        Map<String, Object> params = new HashMap<>();
        params.put("tag", "Auth");
        params.put("retry", true);
        NativeApiRequest req = new NativeApiRequest("req_1", "logger/log", "logEntry", params, 2000L);
        assertEquals("NativeApiRequest requestId", "req_1", req.getRequestId());
        assertEquals("NativeApiRequest path", "logger/log", req.getPath());
        assertEquals("NativeApiRequest action", "logEntry", req.getAction());
        assertEquals("NativeApiRequest string param", "Auth", req.getStringParam("tag", ""));
        assertTrue("NativeApiRequest boolean param", req.getBooleanParam("retry", false));

        // Test 4: NativeApiResponse
        Map<String, Object> resData = new HashMap<>();
        resData.put("status", "logged");
        NativeApiResponse res = NativeApiResponse.success(req.getRequestId(), resData);
        assertTrue("NativeApiResponse isSuccess", res.isSuccess());
        assertEquals("NativeApiResponse statusCode", 200, res.getStatusCode());
        assertEquals("NativeApiResponse data match", "logged", res.getData().get("status"));

        NativeApiError err = new NativeApiError(NativeApiError.CODE_NOT_FOUND, "Route not found", "logger/invalid");
        NativeApiResponse resErr = NativeApiResponse.error("req_2", 404, err);
        assertTrue("NativeApiResponse error not success", !resErr.isSuccess());
        assertEquals("NativeApiResponse error code", NativeApiError.CODE_NOT_FOUND, resErr.getError().getCode());

        // Test 5: LogLevel hierarchy & LogEntry
        assertTrue("LogLevel ERROR loggable at INFO", LogLevel.ERROR.isLoggable(LogLevel.INFO));
        assertTrue("LogLevel INFO loggable at DEBUG", LogLevel.INFO.isLoggable(LogLevel.DEBUG));
        assertTrue("LogLevel DEBUG not loggable at WARN", !LogLevel.DEBUG.isLoggable(LogLevel.WARN));
        assertEquals("LogLevel fromString case-insensitive", LogLevel.WARN, LogLevel.fromString("warn", LogLevel.INFO));

        LogEntry logEntry = new LogEntry(LogLevel.INFO, "Bridge", "NativeBridge initialized");
        assertEquals("LogEntry tag", "Bridge", logEntry.getTag());
        assertEquals("LogEntry level", LogLevel.INFO, logEntry.getLevel());

        // Test 6: AnalyticsEvent & PerformanceMetric
        AnalyticsEvent event = new AnalyticsEvent("purchase_click", Collections.singletonMap("cartId", "cart_999"));
        assertEquals("AnalyticsEvent name", "purchase_click", event.getName());
        assertEquals("AnalyticsEvent param", "cart_999", event.getParameter("cartId"));

        PerformanceMetric metric = new PerformanceMetric("fcp_latency", 245.5, "ms");
        assertEquals("PerformanceMetric name", "fcp_latency", metric.getMetricName());
        assertEquals("PerformanceMetric value", 245.5, metric.getValue());
        assertEquals("PerformanceMetric unit", "ms", metric.getUnit());

        // Test 7: Platform DTOs
        DeviceInfoDTO device = new DeviceInfoDTO("Google", "Pixel 8", "14.0", 34, "uuid-001", 16384);
        assertEquals("DeviceInfoDTO manufacturer", "Google", device.getManufacturer());
        assertEquals("DeviceInfoDTO model", "Pixel 8", device.getModel());
        assertEquals("DeviceInfoDTO sdkInt", 34, device.getSdkInt());

        BatteryStatusDTO battery = new BatteryStatusDTO(92, false, "UNPLUGGED", 31.0f);
        assertEquals("BatteryStatusDTO level", 92, battery.getLevel());
        assertTrue("BatteryStatusDTO not charging", !battery.isCharging());

        // Test 8: UI and Theme contracts
        ToolbarConfig toolbar = new ToolbarConfig(true, "Dashboard", "Sub", "#1E88E5", "#FFFFFF", null);
        assertTrue("ToolbarConfig visible", toolbar.isVisible());
        assertEquals("ToolbarConfig title", "Dashboard", toolbar.getTitle());

        DialogConfig dialog = new DialogConfig("Alert", "Operation succeeded", "OK", "Cancel", true);
        assertEquals("DialogConfig positive text", "OK", dialog.getPositiveButtonText());
        assertTrue("DialogConfig cancelable", dialog.isCancelable());

        // Test 9: Runtime WebSource contracts
        assertEquals("WebSourceType length", 5, WebSourceType.values().length);

        System.out.println("=================================================");
        System.out.println("Results: " + testsPassed + " passed, " + testsFailed + " failed.");
        System.out.println("=================================================");

        if (testsFailed > 0) {
            System.exit(1);
        }
    }
}
