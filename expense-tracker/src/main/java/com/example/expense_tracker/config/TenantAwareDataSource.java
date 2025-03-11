package com.example.expense_tracker.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantAwareDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenantId(); // Get the tenant ID from the context
    }
}
