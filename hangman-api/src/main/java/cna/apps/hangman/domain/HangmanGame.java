package cna.apps.hangman.domain;

public class HangmanGame {

  private final WordProvider wordProvider;

  public HangmanGame(WordProvider wordProvider) {
    this.wordProvider = wordProvider;
  }

  public Game startNewGame() {
    return new Word(wordProvider.provide());
  }

}
