package cna.apps.hangman.usecase;

import cna.apps.hangman.domain.HangmanGame;
import cna.apps.hangman.domain.WordProvider;

public class CreateHangmanGame {

  private final WordProvider wordProvider;

  public CreateHangmanGame(WordProvider wordProvider) {
    this.wordProvider = wordProvider;
  }

  public HangmanGame startNewGame() {
    return new HangmanGame(wordProvider.provide());
  }

}
