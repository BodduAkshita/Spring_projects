package com.example.multi_tenancy2.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantAwareDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // Get the current tenant's ID from the TenantContext
        return TenantContext.getCurrentTenant();
    }
}
