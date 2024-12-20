package br.com.cinepoti.cinepoti_api.security.jwt;

import br.com.cinepoti.cinepoti_api.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilterToken extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilterToken.class); // Logger para logs

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthFilterToken(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = null;
        String username = null;

        try {
            // Obtém o token JWT da requisição
            jwt = getToken(request);
            if (jwt != null) {
                logger.info("Token received: " + jwt);

                // Valida o token JWT
                if (jwtUtils.validateJwtToken(jwt)) {
                    username = jwtUtils.getUserNameToken(jwt);
                    logger.info("Token is valid, user: " + username);

                    // Carrega os detalhes do usuário
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // Cria a autenticação
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    // Define os detalhes da autenticação
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Configura a autenticação no contexto de segurança
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    logger.info("Authentication set for user: " + username);
                } else {
                    logger.warn("Invalid token received");
                }
            } else {
                logger.warn("No token found in request header");
            }

        } catch (Exception e) {
            logger.error("Error processing the token: " + e.getMessage(), e);
        }

        // Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String headerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")) {
            logger.info("Token found in header: " + headerToken);
            return headerToken.replace("Bearer ", "");
        }
        logger.warn("No 'Bearer' token found in the Authorization header.");
        return null;
    }

}
