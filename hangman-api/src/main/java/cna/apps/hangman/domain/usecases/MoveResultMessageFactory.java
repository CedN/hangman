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
