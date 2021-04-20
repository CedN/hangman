package cna.apps.hangman.domain;

public class GameOverException extends Exception {
  private static final long serialVersionUID = 1L;
  private final String wordToGuess;

  public GameOverException(String wordToGuess) {
    this.wordToGuess = wordToGuess;
  }

  public String getWordToGuess() {
    return wordToGuess;
  }
}
