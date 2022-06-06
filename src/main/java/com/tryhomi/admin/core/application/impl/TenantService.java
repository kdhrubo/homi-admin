package com.tryhomi.admin.core.application.impl;


import com.tryhomi.admin.core.application.TenantUseCase;
import com.tryhomi.admin.core.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@RequiredArgsConstructor
@Service
public class TenantService implements TenantUseCase {

    private final DataSource dataSource;
    private final TenantRepository tenantRepository;

    public void initDatabase(String schema) {
        log.info("schema - {}", schema);
        Flyway flyway = Flyway.configure()
                .locations("db/migration/tenant")
                .dataSource(dataSource)
                .schemas(schema)
                .load();

        flyway.migrate();
    }


}