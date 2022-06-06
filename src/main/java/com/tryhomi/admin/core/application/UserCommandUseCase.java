package com.tryhomi.admin.core.application;


import com.tryhomi.admin.core.domain.User;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Locale;

public interface UserCommandUseCase extends UserDetailsService {

    User register(UserRegistrationCommand userRegistrationCommand);

    @Data
    class UserRegistrationCommand {

        String role;
        String firstName;
        String lastName;
        String password;
        String email;

        String url;
        Locale locale;
    }
}
