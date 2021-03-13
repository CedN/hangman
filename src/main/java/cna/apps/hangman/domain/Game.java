package cna.apps.hangman.domain;

public class Game {

  private final WordProvider wordProvider;

  public Game(WordProvider wordProvider) {
    this.wordProvider = wordProvider;
  }

  public Word chooseWord() {
    return new Word(wordProvider.provide());
  }

}
