package com.example.multi_tenancy.config;

import org.springframework.stereotype.Service;

@Service
public class TenantService {

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public void setCurrentTenant(String tenantId) {
        currentTenant.set(tenantId); // Store tenant ID in thread-local variable
    }

    public String getCurrentTenant() {
        return currentTenant.get(); // Get tenant ID from thread-local
    }
}
