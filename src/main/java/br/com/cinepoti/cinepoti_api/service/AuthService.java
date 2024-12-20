package br.com.cinepoti.cinepoti_api.service;

import br.com.cinepoti.cinepoti_api.dto.request.AuthenticationRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.AccessResponseDTO;
import br.com.cinepoti.cinepoti_api.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<AccessResponseDTO> login(AuthenticationRequestDTO authDTO){
        try {
            // Criando token de autenticação
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.password());

            // Autenticando usuário
            Authentication authentication = authenticationManager.authenticate(userAuth);

            // Recuperando detalhes do usuário autenticado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            // Gerando o token JWT
            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            // Retornando o token no corpo da resposta
            return ResponseEntity.ok(new AccessResponseDTO(token));

        } catch (BadCredentialsException e) {
            // Retornando resposta com erro genérico
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AccessResponseDTO("Usuário ou senha inválidos"));
        }
    }
}
