package cna.apps.hangman.tech;

import org.springframework.http.ResponseEntity;

import cna.apps.hangman.api.Error;

public class BadRequestException extends RuntimeException {

  private final int code;
  private final String message;

  public BadRequestException(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public ResponseEntity<Error> getResponseEntity() {
    var error = new Error();
    error.code(code);
    error.message(message);
    return ResponseEntity.badRequest().body(error);
  }

}
