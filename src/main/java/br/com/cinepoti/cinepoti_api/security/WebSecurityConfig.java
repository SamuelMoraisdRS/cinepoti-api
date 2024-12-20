package br.com.cinepoti.cinepoti_api.security;

import br.com.cinepoti.cinepoti_api.security.jwt.AuthEntryPointJWT;
import br.com.cinepoti.cinepoti_api.security.jwt.AuthFilterToken;
import br.com.cinepoti.cinepoti_api.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final AuthEntryPointJWT unauthorizedHandler;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(AuthEntryPointJWT unauthorizedHandler, JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        logger.info("WebSecurityConfig initialized with AuthEntryPointJWT, JwtUtils, and UserDetailsService.");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        logger.debug("Initializing PasswordEncoder (BCryptPasswordEncoder).");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        logger.debug("Configuring AuthenticationManager.");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthFilterToken authFilterToken(){
        logger.debug("Creating AuthFilterToken bean.");
        return new AuthFilterToken(jwtUtils, userDetailsService);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        logger.info("Configuring SecurityFilterChain.");

        http.cors(Customizer.withDefaults());
        http.csrf(crsf -> crsf.disable())
                .exceptionHandling(exption -> {
                    logger.info("Setting up exception handling with AuthEntryPointJWT.");
                    exption.authenticationEntryPoint(unauthorizedHandler);
                })
                .sessionManagement(session -> {
                    logger.info("Configuring session management to be stateless.");
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(auth -> {
                    logger.info("Configuring authorization rules.");
                    auth.requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/usuarios/**").permitAll()
                            .anyRequest().authenticated();
                });

        logger.debug("Adding AuthFilterToken to the filter chain.");
        http.addFilterBefore(authFilterToken(), UsernamePasswordAuthenticationFilter.class);

        logger.info("SecurityFilterChain configured successfully.");
        return http.build();
    }
}
