package br.com.watchwatt.watchwatt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FailedDependencyException extends ResponseStatusException {
  public FailedDependencyException(String message, Throwable throwable) {
    super(HttpStatus.FAILED_DEPENDENCY, message, throwable);
  }
}
