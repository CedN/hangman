package cna.apps.hangman.tech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import cna.apps.hangman.api.Error;

public class ResponseEntityExceptionHandlerShould {

  @Test
  void return_400_reponse_with_code_and_message_from_BadRquestException() {
    int code = 100;
    String message = "bad request message";
    var badRequestException = new BadRequestException(code, message);
    ResponseEntity<Error> responseEntity = new ResponseEntityExceptionHander().handle(badRequestException,
        mockWebRequest());
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(code, responseEntity.getBody().getCode());
    assertEquals(message, responseEntity.getBody().getMessage());
  }

  private WebRequest mockWebRequest() {
    return new ServletWebRequest(new MockHttpServletRequest());
  }

}
