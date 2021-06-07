/*
BadRequestExceptionHandlerShould.java
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import cna.apps.hangman.api.Error;

public class BadRequestExceptionHandlerShould {

  @Test
  void return_400_reponse_with_code_and_message_from_BadRquestException() {
    int code = 100;
    String message = "bad request message";
    var badRequestException = new BadRequestException(code, message);
    ResponseEntity<Error> responseEntity = new BadRequestExceptionHandler().handle(badRequestException,
        mockWebRequest());
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(code, responseEntity.getBody().getCode());
    assertEquals(message, responseEntity.getBody().getMessage());
  }

  private WebRequest mockWebRequest() {
    return new ServletWebRequest(new MockHttpServletRequest());
  }

}
