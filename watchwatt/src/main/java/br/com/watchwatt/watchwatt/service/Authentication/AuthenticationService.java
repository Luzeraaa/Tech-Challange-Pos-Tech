package br.com.watchwatt.watchwatt.service.Authentication;

import br.com.watchwatt.watchwatt.dto.authentication.SigninDTO;
import br.com.watchwatt.watchwatt.dto.authentication.JwtAuthenticationDTO;

public interface AuthenticationService {
    JwtAuthenticationDTO signin(SigninDTO request);
}
