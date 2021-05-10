package cna.apps.hangman.tech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cna.apps.hangman.api.Error;

public class ErrorAssertions {
  
  public static void assertError(ResponseEntity<Error> responseEntity, int code, String message) {
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    Error error = responseEntity.getBody();
    assertEquals(code, error.getCode());
    assertEquals(message, error.getMessage());
  }
  
}
