package com.tryhomi.admin.core.config;



import com.tryhomi.admin.core.application.UserCommandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserCommandUseCase userCommandUseCase;
    private final PasswordEncoder passwordEncoder;

    private String WHITE_LIST [] = {
            "/signin","/authenticate","/webjars/**",
            "/register","/actuator/health", "/signup",
            "/js/**","/assets/**","/billing/calculator",
            "/css/**",
            "/fonts/**",
            "/images/**"
    };

    final CustomAuthenticationEntryPoint customAuthenticationEntryPoint =
            new CustomAuthenticationEntryPoint("/signin");

    final CustomExceptionTranslationFilter customExceptionTranslationFilter =
            new CustomExceptionTranslationFilter(customAuthenticationEntryPoint);

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userCommandUseCase);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(customExceptionTranslationFilter, ExceptionTranslationFilter.class)
                .authorizeRequests()
                    .antMatchers(WHITE_LIST).permitAll()
                    .antMatchers("/**").hasRole("ADMIN")
                    .and()
                .formLogin()
                    .loginProcessingUrl("/authenticate")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/signin").permitAll()
                    .defaultSuccessUrl("/dashboard")
                    .failureUrl("/signin?failed")
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
                    .logoutSuccessUrl("/signin?logout")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .and()
                .csrf().and().exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint);

        http.headers().frameOptions().sameOrigin();
    }

}
