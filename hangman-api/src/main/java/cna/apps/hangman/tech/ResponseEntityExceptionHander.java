package cna.apps.hangman.tech;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cna.apps.hangman.api.Error;

@ControllerAdvice
public class ResponseEntityExceptionHander extends ResponseEntityExceptionHandler {

  @ExceptionHandler
  protected ResponseEntity<Error> handle(BadRequestException badRequestException, WebRequest request) {
    return badRequestException.getResponseEntity();
  }

}
