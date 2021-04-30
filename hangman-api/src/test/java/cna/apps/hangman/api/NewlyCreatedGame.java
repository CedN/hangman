package cna.apps.hangman.api;

import cna.apps.hangman.domain.GameAlreadyWonException;
import cna.apps.hangman.domain.GameOverException;
import cna.apps.hangman.domain.Hangman;

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
  public void tryLetter(char c) throws GameOverException, GameAlreadyWonException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isTheGoodWord(String proposal) throws GameOverException, GameAlreadyWonException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Hangman getHangMan() {
    return null;
  }
  
}
