package com.tryhomi.admin.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    private AccessDecisionManager accessDecisionManager;
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web
                .ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/webjars/**")
                .antMatchers("/setup**")
                .antMatchers("/signup**");
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.antMatcher("/**")
                .authorizeRequests()
                .accessDecisionManager(accessDecisionManager)
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?failed")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .and()
                .headers()
                .frameOptions().disable()
                .cacheControl().disable()
                .httpStrictTransportSecurity().disable()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedPage("/login");
        // @formatter:on
    }
}