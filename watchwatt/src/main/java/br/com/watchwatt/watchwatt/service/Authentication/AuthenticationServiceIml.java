package br.com.watchwatt.watchwatt.service.Authentication;

import br.com.watchwatt.watchwatt.dao.user.UserRepository;
import br.com.watchwatt.watchwatt.dto.authentication.JwtAuthenticationDTO;
import br.com.watchwatt.watchwatt.dto.authentication.SigninDTO;
import br.com.watchwatt.watchwatt.exception.ForbiddenException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceIml implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceIml(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtServiceImpl jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtAuthenticationDTO signin(SigninDTO signinDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinDTO.email(), signinDTO.password()));
            var user = userRepository.findByEmail(signinDTO.email());
            var jwt = jwtService.generateToken(user);
            return new JwtAuthenticationDTO(jwt);
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            throw new ForbiddenException("negado");
        }
    }
}
