package com.example.expense_tracker.config;

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
        HikariDataSource tenant1DataSource = createDataSource(tenant1Url, tenant1Username, tenant1Password);
        // Tenant 2 DataSource using HikariCP
        HikariDataSource tenant2DataSource = createDataSource(tenant2Url, tenant2Username, tenant2Password);

        targetDataSources.put("tenant1", tenant1DataSource);
        targetDataSources.put("tenant2", tenant2DataSource);

        // Custom Tenant-Aware DataSource
        TenantAwareDataSource dataSource = new TenantAwareDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(tenant1DataSource);
        dataSource.afterPropertiesSet();

        // Initialize schema for both tenants
        initializeSchema(tenant1DataSource);
        initializeSchema(tenant2DataSource);

        return dataSource;
    }

    /**
     * Creates a HikariCP DataSource with the given configuration.
     */
    private HikariDataSource createDataSource(String url, String username, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

    /**
     * Initialize schema dynamically for each tenant.
     */
    private void initializeSchema(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {

            // Create `user` table
            String createUserTable = """
                CREATE TABLE IF NOT EXISTS user (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    full_name VARCHAR(255) NOT NULL,
                    username VARCHAR(255) UNIQUE NOT NULL,
                    email VARCHAR(255) UNIQUE NOT NULL,
                    phone_number VARCHAR(20) UNIQUE NOT NULL,
                    password VARCHAR(255) NOT NULL,
                    tenant_id VARCHAR(50) NOT NULL
                );
            """;
            stmt.executeUpdate(createUserTable);

            // Create `expense` table
            String createExpenseTable = """
                CREATE TABLE IF NOT EXISTS expense (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    amount DECIMAL(10,2) NOT NULL,
                    category VARCHAR(255) NOT NULL,
                    user_id BIGINT NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
                );
            """;
            stmt.executeUpdate(createExpenseTable);

            System.out.println("Schema initialized for: " + conn.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
