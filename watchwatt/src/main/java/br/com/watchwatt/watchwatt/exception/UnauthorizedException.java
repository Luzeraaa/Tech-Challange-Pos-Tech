package br.com.watchwatt.watchwatt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {
  public UnauthorizedException(String message) {
    super(HttpStatus.UNAUTHORIZED, message, null);
  }
}
