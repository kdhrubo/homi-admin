package com.tryhomi.admin.core.multitenancy;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
class TenantConnectionProvider implements MultiTenantConnectionProvider {

    private DataSource datasource;

    public TenantConnectionProvider(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        log.debug("tenantIdentifier - {}", tenantIdentifier);
        final Connection connection = getAnyConnection();
        connection.createStatement()
                .execute(String.format("SET search_path TO  '%s' , '%s';", tenantIdentifier, TenantContext.DEFAULT_TENANT_ID));
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        log.debug("tenantIdentifier - {}", tenantIdentifier);
        connection.createStatement()
                .execute(String.format("SET search_path TO '%s';", TenantContext.DEFAULT_TENANT_ID));
        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}