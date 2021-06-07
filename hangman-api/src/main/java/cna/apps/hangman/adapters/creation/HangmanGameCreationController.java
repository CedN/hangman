/*
HangmanGameCreationController.java
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
package cna.apps.hangman.adapters.creation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cna.apps.hangman.domain.ports.create.CreateHangmanGameInputBoundary;

public class HangmanGameCreationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HangmanGameCreationController.class);

  private CreateHangmanGameInputBoundary usecase;

  public HangmanGameCreationController(CreateHangmanGameInputBoundary usecase) {
    this.usecase = usecase;
  }

  public void createNewGame() {
    LOGGER.info("Start a new game");
    usecase.startNewGame();
  }
  
}
