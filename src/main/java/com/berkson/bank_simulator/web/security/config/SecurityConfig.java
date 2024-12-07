package com.berkson.bank_simulator.web.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ContentSecurityPolicyHeaderWriter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * Created By : Berkson Ximenes
 * Date : 07/12/2024
 **/

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private static final String DEFAULT_SRC_SELF_POLICY = "default-src 'self'; frame-ancestors 'self'; " +
            "form-action 'self'; font-src 'self' fonts.googleapis.com fonts.gstatic.com; " +
            "style-src 'self' 'unsafe-inline' fonts.googleapis.com fonts.gstatic.com";

    public SecurityConfig(UserDetailsService userDetailsService,
                          AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    @Primary
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers((headers) -> headers.addHeaderWriter(writer()))
                .csrf(c -> {
                    c.ignoringRequestMatchers("/api/v1/**");
                    c.csrfTokenRequestHandler(new CustomCsrfTokenRequestAttributeHandler());
                    c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                })
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(antMatcher("/index.html"), antMatcher("/"), antMatcher("/favicon.ico"), antMatcher("/home"),
                                antMatcher("/login"), antMatcher("/*.css"), antMatcher("/*.js*"), antMatcher("/assets/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic((httpBasic) -> httpBasic.authenticationEntryPoint(authenticationEntryPoint))
                .logout((logout) -> logout
                        .logoutUrl("/webapp/logout").permitAll()
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true));


        return http.build();
    }

    @Bean
    @Primary
    AuthenticationManagerBuilder authenticationManager(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return auth;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ContentSecurityPolicyHeaderWriter writer() {
        return new ContentSecurityPolicyHeaderWriter(DEFAULT_SRC_SELF_POLICY);
    }
}
