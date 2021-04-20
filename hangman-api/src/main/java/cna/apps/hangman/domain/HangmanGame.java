package cna.apps.hangman.domain;

public class HangmanGame {

  private final WordProvider wordProvider;

  public HangmanGame(WordProvider wordProvider) {
    this.wordProvider = wordProvider;
  }

  public Word chooseWord() {
    return new Word(wordProvider.provide());
  }

}
