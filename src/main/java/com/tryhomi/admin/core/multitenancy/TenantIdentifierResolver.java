package com.tryhomi.admin.core.multitenancy;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

        
      @Override
      public String resolveCurrentTenantIdentifier() {
          log.debug("Resolving current tenant");
          String tenant =  Optional.ofNullable(TenantContext.getCurrentTenant())
                                 .orElse(TenantContext.DEFAULT_TENANT_ID);

          log.debug("Resolving current tenant - {}", tenant);

          return tenant;
      }
 
      @Override
      public boolean validateExistingCurrentSessions() {
               return true;
      }
 
}