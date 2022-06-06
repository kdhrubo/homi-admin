package com.tryhomi.admin.core.application.impl;

import com.tryhomi.admin.core.application.UserCommandUseCase;
import com.tryhomi.admin.core.domain.Role;
import com.tryhomi.admin.core.domain.SimpleUser;
import com.tryhomi.admin.core.domain.Tenant;
import com.tryhomi.admin.core.domain.User;
import com.tryhomi.admin.core.event.EventPublisher;
import com.tryhomi.admin.core.event.UserRegisteredEvent;
import com.tryhomi.admin.core.repository.RoleRepository;
import com.tryhomi.admin.core.repository.TenantRepository;
import com.tryhomi.admin.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
class UserCommandService implements UserCommandUseCase {

    private final TenantService tenantService;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final EventPublisher eventPublisher;

    @Transactional
    @Override
    public User register(UserRegistrationCommand userRegistrationCommand) {

        Optional<Role> role = this.roleRepository.findByName(userRegistrationCommand.getRole());

        if(role.isEmpty()) {
            throw new RuntimeException("Role not found.");
        }

        Tenant tenant = new Tenant();
        tenant.setTenantId(UUID.randomUUID().toString());
        tenant = tenantRepository.save(tenant);

        log.info("## tenant - {}", tenant);
        TreeSet roles = new TreeSet();
        roles.add(role.get());

        User user = User.builder()
                .email(userRegistrationCommand.getEmail())
                .enabled(false)
                .tenant(tenant)
                .verificationToken(UUID.randomUUID().toString())
                .verificationTokenExpiry(LocalDateTime.now().plusDays(1))
                .password(encoder.encode(userRegistrationCommand.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        this.tenantService.initDatabase(tenant.getTenantId());

        eventPublisher.publish(UserRegisteredEvent.builder()
                .locale(userRegistrationCommand.getLocale())
                .token(user.getVerificationToken())
                .url(userRegistrationCommand.getUrl())
                .user(user)
                .build()
        );

        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        log.info("Found user , is account enabled - {}", user.get().isEnabled());

        return new SimpleUser(user.get().getEmail(),
                user.get().getPassword(), user.get().isEnabled(), mapRolesToAuthorities(user.get().getRoles()), user.get());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
