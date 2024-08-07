package ru.bitoche.basemarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.bitoche.basemarket.services.AppUserDetailsService;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(){
        return new AppUserDetailsService();
    }
    @Bean
    public SecurityContextRepository securityContextRepository() {return new DelegatingSecurityContextRepository(
            new RequestAttributeSecurityContextRepository(),
            new HttpSessionSecurityContextRepository());
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityContextRepository securityContextRepository) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        Objects.requireNonNull(authorizeHttpRequests ->
                                authorizeHttpRequests.
                                        requestMatchers("/css/**", "/js/**", "/res/**", "./templates/schemes/**", "attachments", "static/favicon.ico").permitAll().
                                        requestMatchers("/", "/check-email", "/login", "/register", "/confirm-account").permitAll().
                                        requestMatchers("/adm/**").hasAuthority("ADMIN").
                                        requestMatchers("/mod/**", "/mod").hasAuthority("MODER").
                                        requestMatchers("/profile").authenticated().
                                        anyRequest().permitAll())
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) ->
                        logout.logoutUrl("/logout").
                                logoutSuccessUrl("/").
                                invalidateHttpSession(true)
                )
                .securityContext(
                        securityContext -> securityContext.
                                securityContextRepository(securityContextRepository)
                );
        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
