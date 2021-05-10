package cna.apps.hangman.tech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cna.apps.hangman.api.Error;

@ControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(BadRequestExceptionHandler.class);

  @ExceptionHandler
  protected ResponseEntity<Error> handle(BadRequestException badRequestException, WebRequest request) {
    ResponseEntity<Error> responseEntity = badRequestException.getResponseEntity();
    LOGGER.info("Bad request occured = '{}'", responseEntity);
    return responseEntity;
  }

}
