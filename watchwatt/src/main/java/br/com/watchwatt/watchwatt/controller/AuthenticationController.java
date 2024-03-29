package br.com.watchwatt.watchwatt.controller;

import br.com.watchwatt.watchwatt.dto.authentication.JwtAuthenticationDTO;
import br.com.watchwatt.watchwatt.dto.authentication.SigningDTO;
import br.com.watchwatt.watchwatt.service.Authentication.AuthenticationServiceIml;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public record AuthenticationController(
        AuthenticationServiceIml authenticationService
) implements Controller {

    @PostMapping(headers = X_API_VERSION_1)
    public ResponseEntity<JwtAuthenticationDTO> signing(@RequestBody SigningDTO signingDTO) {
        return ResponseEntity.ok(authenticationService.signing(signingDTO));
    }
}
