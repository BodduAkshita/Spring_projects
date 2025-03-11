package com.example.multi_tenancy2.config;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Value("${spring.datasource.tenant1.url}")
    private String tenant1Url;

    @Value("${spring.datasource.tenant1.username}")
    private String tenant1Username;

    @Value("${spring.datasource.tenant1.password}")
    private String tenant1Password;

    @Value("${spring.datasource.tenant2.url}")
    private String tenant2Url;

    @Value("${spring.datasource.tenant2.username}")
    private String tenant2Username;

    @Value("${spring.datasource.tenant2.password}")
    private String tenant2Password;

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();

        // Tenant 1 DataSource using HikariCP
        HikariConfig tenant1Config = new HikariConfig();
        tenant1Config.setJdbcUrl(tenant1Url);
        tenant1Config.setUsername(tenant1Username);
        tenant1Config.setPassword(tenant1Password);
        HikariDataSource tenant1DataSource = new HikariDataSource(tenant1Config);

        // Tenant 2 DataSource using HikariCP
        HikariConfig tenant2Config = new HikariConfig();
        tenant2Config.setJdbcUrl(tenant2Url);
        tenant2Config.setUsername(tenant2Username);
        tenant2Config.setPassword(tenant2Password);
        HikariDataSource tenant2DataSource = new HikariDataSource(tenant2Config);

        targetDataSources.put("tenant1", tenant1DataSource);
        targetDataSources.put("tenant2", tenant2DataSource);

        // Custom Tenant-Aware DataSource
        TenantAwareDataSource dataSource = new TenantAwareDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(tenant1DataSource);
        dataSource.afterPropertiesSet();

        // 🔥 Initialize schema for both tenants
        initializeSchema(tenant1DataSource);
        initializeSchema(tenant2DataSource);

        return dataSource;
    }

    /**
     * ✅ Initialize schema dynamically for each tenant.
     */
    private void initializeSchema(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            String createTableQuery = """
                CREATE TABLE IF NOT EXISTS expense (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    amount DECIMAL(10,2) NOT NULL,
                    tenant_id VARCHAR(50) NOT NULL
                );
            """;
            stmt.executeUpdate(createTableQuery);

            System.out.println("✅ Schema initialized for: " + conn.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
