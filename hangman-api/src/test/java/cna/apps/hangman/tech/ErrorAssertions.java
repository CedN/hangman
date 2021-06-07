/*
ErrorAssertions.java
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
