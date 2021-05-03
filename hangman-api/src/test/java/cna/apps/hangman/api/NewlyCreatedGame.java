package cna.apps.hangman.api;

import cna.apps.hangman.domain.core.GameOverException;
import cna.apps.hangman.domain.core.Hangman;

public class NewlyCreatedGame implements Game {

  private final String mask;

  public NewlyCreatedGame(String mask) {
    this.mask = mask;
  }

  @Override
  public String getMask() {
    return mask;
  }

  @Override
  public void tryLetter(char c) throws GameOverException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isTheGoodWord(String proposal) throws GameOverException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Hangman getHangMan() {
    return null;
  }
  
}
