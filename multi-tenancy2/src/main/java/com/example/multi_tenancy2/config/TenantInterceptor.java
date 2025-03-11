package com.example.multi_tenancy2.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = extractTenantIdFromRequest(request);  // Extract tenant ID from request
        TenantContext.setCurrentTenant(tenantId);  // Set the current tenant in TenantContext
        return true;
    }

    private String extractTenantIdFromRequest(HttpServletRequest request) {
        // Extract tenant ID from request header
        String tenantId = request.getHeader("X-Tenant-ID");
        if (tenantId == null || tenantId.isEmpty()) {
            tenantId = "default";  // Default to "default" if no tenant ID is provided
        }
        return tenantId;
    }
}
