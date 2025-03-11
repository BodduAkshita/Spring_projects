package com.example.multi_tenancy2.config;

public class TenantContext {

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    // Set the current tenant
    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    // Get the current tenant
    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    // Clear the current tenant after the request is completed
    public static void clear() {
        currentTenant.remove();
    }
}
