package com.tryhomi.admin.core.multitenancy;


import com.tryhomi.admin.core.domain.SimpleUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtil {

    private SecurityUtil(){}

    public static Optional<SimpleUser> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            SimpleUser user = (SimpleUser)authentication.getPrincipal();


            return Optional.of(user);
        }

        return Optional.empty();
    }
}
