package br.com.cinepoti.cinepoti_api.service;


import br.com.cinepoti.cinepoti_api.dto.request.AuthenticationRequestDTO;
import br.com.cinepoti.cinepoti_api.dto.response.AccessResponseDTO;
import br.com.cinepoti.cinepoti_api.security.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = new JwtUtils();
    }

    public AccessResponseDTO login(AuthenticationRequestDTO authDTO){

        try {
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.password());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            return new AccessResponseDTO(token);

        }catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid username/password");
        }


    }
}
