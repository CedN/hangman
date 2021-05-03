package cna.apps.hangman.domain.ports.proposal;

import cna.apps.hangman.domain.core.WordToGuess;

public class LostGame {

  private final String wordToGuess;
  private final int hangmanStep;
  private final String mask;

  public LostGame(WordToGuess wordToGuess, int hangmanStep, String mask) {
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

}
