package cna.apps.hangman.domain.entities;

public class GameOverException extends Exception {
  private static final long serialVersionUID = 1L;
  private boolean isWonGame;

  GameOverException(boolean isWonGame) {
    this.isWonGame = isWonGame;}

  public boolean isWonGame() {
    return isWonGame;
  }
}
