package com.example.multi_tenancy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Autowired
    private TenantService tenantService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tenantId = extractTenantIdFromRequest(request);  // Extract tenant ID from request (e.g., from subdomain or header)
        tenantService.setCurrentTenant(tenantId);  // Set the current tenant in the TenantService
        return true;
    }

    private String extractTenantIdFromRequest(HttpServletRequest request) {
        // You can extract the tenant ID from the subdomain, request header, or URL. Here, we'll assume it comes from the header.
        String tenantId = request.getHeader("X-Tenant-ID");
        if (tenantId == null) {
            tenantId = "default";  // Set a default tenant ID if none is provided
        }
        return tenantId;
    }
}
