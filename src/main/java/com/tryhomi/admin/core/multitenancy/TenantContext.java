package com.tryhomi.admin.core.multitenancy;

public abstract class TenantContext {

    public static final String DEFAULT_TENANT_ID = "nirvanacore";

    private static ThreadLocal<String> currentTenant = new ThreadLocal<String>();

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }

}