package com.tryhomi.admin.core.repository;


import com.tryhomi.admin.core.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}