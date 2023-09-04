package br.com.watchwatt.watchwatt.service.Authentication;

import br.com.watchwatt.watchwatt.dto.authentication.JwtAuthenticationDTO;
import br.com.watchwatt.watchwatt.dto.authentication.SigningDTO;

public interface AuthenticationService {
    JwtAuthenticationDTO signing(SigningDTO request);
}
