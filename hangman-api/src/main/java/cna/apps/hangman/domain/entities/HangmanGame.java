package cna.apps.hangman.domain.entities;

import static cna.apps.hangman.domain.entities.MoveResult.GOOD;
import static cna.apps.hangman.domain.entities.MoveResult.WON_GAME;

public class HangmanGame {

  private final WordToGuess wordToGuess;
  private final Mask mask;
  private final Hangman hangman;

  public HangmanGame(String wordToGuess) {
    this.wordToGuess = new WordToGuess(wordToGuess);
    mask = new Mask();
    hangman = new Hangman();
  }

  public String getMask() {
    return mask.showMaskedWord(wordToGuess);
  }

  public MoveResult tryLetter(char c) throws GameOverException {
    assertGameNotOver();
    if (wordToGuess.contains(c) && !mask.isLetterAlreadyFound(c)) {
      return processLetterFound(c);
    }
    return hangman.increaseStep();
  }

  private void assertGameNotOver() throws GameOverException {
    if (isGameWon() || isGameLost()) {
      throw new GameOverException(isGameWon());
    }
  }

  private MoveResult processLetterFound(char c) {
    mask.addLetterFound(c);
    if (isGameWon()) {
      return WON_GAME;
    }
    return GOOD;
  }

  private boolean isGameWon() {
    return mask.isDiscovered(wordToGuess);
  }

  private boolean isGameLost() {
    return hangman.isFull();
  }

  public Hangman getHangMan() {
    return hangman;
  }

  public WordToGuess getWordToGuess() {
    return wordToGuess;
  }

}
