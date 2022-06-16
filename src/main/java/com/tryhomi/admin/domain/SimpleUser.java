package com.tryhomi.admin.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SimpleUser extends User {
    private com.tryhomi.admin.domain.User user;

    public SimpleUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public SimpleUser(String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities, com.tryhomi.admin.domain.User user) {

        super(username, password, enabled, true, true, true, authorities);
        this.user = user;
    }



    public com.tryhomi.admin.domain.User getUser() {
        return this.user;
    }
}
