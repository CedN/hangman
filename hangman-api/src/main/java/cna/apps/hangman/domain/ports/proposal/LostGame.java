package cna.apps.hangman.domain.ports.proposal;

import cna.apps.hangman.domain.entities.WordToGuess;

public class LostGame {

  private final String wordToGuess;
  private final int hangmanStep;
  private final String mask;
  private final String message;

  public LostGame(String message, WordToGuess wordToGuess, int hangmanStep, String mask) {
    this.message = message;
    this.wordToGuess = wordToGuess.getValue();
    this.hangmanStep = hangmanStep;
    this.mask = mask;
  }

  public String getWordToGuess() {
    return wordToGuess;
  }

  public Integer getHangmanStep() {
    return hangmanStep;
  }

  public String getMask() {
    return mask;
  }

  public String getMessage() {
    return message;
  }

}
