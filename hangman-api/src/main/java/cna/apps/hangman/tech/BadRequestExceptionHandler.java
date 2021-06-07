/*
BadRequestExceptionHandler.java
Copyright 2021 @CedN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
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
