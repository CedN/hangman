/*
MoveResultMessageFactory.java
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
package cna.apps.hangman.domain.usecases;

import java.util.Map;

import cna.apps.hangman.domain.entities.MoveResult;

public class MoveResultMessageFactory {
  
  private static final Map<MoveResult, String> MOVE_RESULT_MESSAGE_PATTERN = Map.of(
    MoveResult.GOOD, "You have found the letter '%c'!",
    MoveResult.WRONG, "The letter '%c' is not in the word to guess!",
    MoveResult.LOST_GAME, "You lost the game by proposing the wrong letter '%c'! The word to guess was '%s'.",
    MoveResult.WON_GAME, "You won the game!"
  );

  public String getMessage(MoveResult moveResult, Object... parameters) {
    String messagePattern = MOVE_RESULT_MESSAGE_PATTERN.get(moveResult);
    return String.format(messagePattern, parameters);
  }

}
