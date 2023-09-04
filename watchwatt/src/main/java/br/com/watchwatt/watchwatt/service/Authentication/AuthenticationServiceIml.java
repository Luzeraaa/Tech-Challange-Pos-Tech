package br.com.watchwatt.watchwatt.service.Authentication;

import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.dto.authentication.JwtAuthenticationDTO;
import br.com.watchwatt.watchwatt.dto.authentication.SigningDTO;
import br.com.watchwatt.watchwatt.exception.ForbiddenException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceIml implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    private static final String ACCESS_DENIED = "Access denied";

    public AuthenticationServiceIml(
            UserRepository userRepository,
            JwtServiceImpl jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationDTO signing(SigningDTO signingDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signingDTO.email(), signingDTO.password()));
            var user = userRepository.findByEmail(signingDTO.email());
            var jwt = jwtService.generateToken(user);

            return new JwtAuthenticationDTO(jwt);

        } catch (AuthenticationException ex) {
            throw new ForbiddenException(ACCESS_DENIED);
        }
    }
}
