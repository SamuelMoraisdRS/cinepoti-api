package br.com.cinepoti.cinepoti_api.security.jwt;

import br.com.cinepoti.cinepoti_api.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LogManager.getLogger(JwtUtils.class);

    @Value("${cinepoti.jwtSecret}")
    private String jwstSecret;

    @Value("${cinepoti.jwtExpirationMS}")
    private Long jwtExpirationMS;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetail) {
        logger.info("Generating token for user: {}", userDetail.getUsername());
        String token = Jwts.builder()
                .setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMS))
                .signWith(getSignedKey(), SignatureAlgorithm.HS512)
                .compact();
        logger.debug("Token generated successfully: {}", token);
        return token;
    }

    public Key getSignedKey() {
        logger.debug("Retrieving signing key...");
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwstSecret));
    }

    public boolean validateJwtToken(String token) {
        try {
            logger.info("Validating JWT token...");
            Jwts.parser()
                    .setSigningKey(getSignedKey())
                    .build()
                    .parseClaimsJws(token);
            logger.info("Token is valid.");
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
